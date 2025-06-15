package com.example.backend.service;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.LikePostRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService {

    @Autowired
    private LikePostRepository likePostRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    public String toggleLike(Integer postId, Integer userId) {

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        Like like = likePostRepository.findByPostAndUser(post, usuario).orElse(null);

        if (like != null) {
            likePostRepository.delete(like);
            return "Descurtido com sucesso";
        } else {
            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(usuario);
            likePostRepository.save(newLike);
            return "Curtido com sucesso";
        }
    }
}
