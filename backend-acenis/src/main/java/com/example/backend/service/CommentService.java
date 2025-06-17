package com.example.backend.service;

import com.example.backend.dto.CommentRequest;
import com.example.backend.model.Comment;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Comment criarComentario(CommentRequest commentRequest) {

        Optional<Post> postOptional = postRepository.findById(commentRequest.getIdPost());
        if (postOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado com o ID: " + commentRequest.getIdPost());
        }
        Post post = postOptional.get();

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(commentRequest.getIdUser());
        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + commentRequest.getIdUser());
        }
        Usuario usuario = usuarioOptional.get();

        Comment comentario = new Comment();
        comentario.setContent(commentRequest.getContent());
        comentario.setPost(post);
        comentario.setUsuario(usuario);

        return commentRepository.save(comentario);
    }

    public List<Comment> listarComentariosPorPost(Integer idPost) {
        return commentRepository.findByPostId(idPost);
    }
}