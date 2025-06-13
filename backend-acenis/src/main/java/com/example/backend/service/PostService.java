package com.example.backend.service;

import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Post criarPost(Integer idUser, String conteudo) {
        Usuario autor = usuarioRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Post post = new Post();
        post.setAutor(autor);
        post.setConteudo(conteudo);
        post.setDataCriacao(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> listarPostsPorUsuario(Integer idUser) {
        return postRepository.findByAutorIdUser(idUser);
    }

    public List<Post> listarTodos() {
        return postRepository.findAll();
    }
}
