package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.dto.CommentRequest;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment criarComentario(@RequestBody CommentRequest commentRequest) {

        Integer idUser = commentRequest.getIdUser();
        Integer idPost = commentRequest.getIdPost();
        String conteudo = commentRequest.getContent();

        return commentService.criarComentario(idUser, idPost, conteudo);
    }

    @GetMapping("/post/{idPost}")
    public List<Comment> listarComentariosPorPost(@PathVariable Integer idPost) {
        return commentService.listarComentariosPorPost(idPost);
    }

}
