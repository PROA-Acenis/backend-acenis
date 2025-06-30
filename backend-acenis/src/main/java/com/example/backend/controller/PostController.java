package com.example.backend.controller;

import com.example.backend.dto.PostRequest;
import com.example.backend.dto.PostResponse;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.service.PostService;
import com.example.backend.service.LikePostService;
import com.example.backend.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final UsuarioRepository usuarioRepository;
    private final PostService postService;
    private final LikePostService likePostService;
    private final JwtService jwtService;

    public PostController(UsuarioRepository usuarioRepository, PostService postService, LikePostService likePostService, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.postService = postService;
        this.likePostService = likePostService;
        this.jwtService = jwtService;
    }
    @PreAuthorize("hasRole('RESPONSAVEL') or hasRole('FORNECEDOR') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(postRequest.getIdUser());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Usuario autor = usuarioOpt.get();

        Post newPost = new Post();
        newPost.setConteudo(postRequest.getConteudo());
        newPost.setAutor(autor);
        newPost.setDataCriacao(LocalDateTime.now());

        Post savedPost = postService.createPost(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(required = false) Integer userId) {
        List<PostResponse> posts = postService.getAllPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(
            @PathVariable Integer userId,
            @RequestParam(required = false) Integer currentUserId) {
        List<PostResponse> posts = postService.getPostsByAutorId(userId, currentUserId);
        return ResponseEntity.ok(posts);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}/like")
    public ResponseEntity<?> toggleLike(
            @PathVariable Integer postId,
            HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userEmail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            userEmail = jwtService.extractUsername(token);
        }

        if (userEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Token JWT inválido ou usuário não encontrado."));
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailUser(userEmail);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Usuário do token não encontrado no banco de dados."));
        }
        Integer userId = usuarioOpt.get().getIdUser();

        try {
            likePostService.toggleLike(postId, userId);

            boolean isLiked = likePostService.isLiked(postId, userId);
            int newLikesCount = likePostService.getLikesCount(postId);

            return ResponseEntity.ok(Map.of(
                    "likedByUser", isLiked,
                    "likesCount", newLikesCount,
                    "message", isLiked ? "Post curtido!" : "Post descurtido!"
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro interno ao processar like: " + e.getMessage()));
        }
    }


    @PreAuthorize("hasRole('ADMIN') or @postService.isPostAuthor(#id, authentication.principal.username)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        if (!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}