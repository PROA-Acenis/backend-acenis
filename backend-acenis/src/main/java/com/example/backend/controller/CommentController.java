package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.dto.CommentRequest;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<Comment> criarComentario(@RequestBody CommentRequest commentDto) {
        Comment comentarioSalvo = commentService.criarComentario(commentDto);
        return new ResponseEntity<>(comentarioSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/comments/post/{idPost}")
    public ResponseEntity<List<Comment>> listarComentariosPorPost(@PathVariable Integer idPost) {
        List<Comment> comentarios = commentService.listarComentariosPorPost(idPost);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

}