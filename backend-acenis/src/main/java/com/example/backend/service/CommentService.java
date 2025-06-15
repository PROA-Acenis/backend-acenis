package com.example.backend.service;

import com.example.backend.model.Comment;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Comment criarComentario(Integer idUser, Integer idPost, String conteudo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUser);
        Optional<Post> postOptional = postRepository.findById(idPost);

        if (usuarioOptional.isPresent() && postOptional.isPresent()) {
            Comment comentario = new Comment();
            comentario.setUsuario(usuarioOptional.get());
            comentario.setPost(postOptional.get());
            comentario.setContent(conteudo);
            comentario.setCreatedAt(LocalDateTime.now());
            return commentRepository.save(comentario);
        } else {
            throw new RuntimeException("Usuário ou Post não encontrado.");
        }
    }

    public List<Comment> listarComentariosPorPost(Integer idPost) {
        return commentRepository.findByPostId(idPost);
    }

}
