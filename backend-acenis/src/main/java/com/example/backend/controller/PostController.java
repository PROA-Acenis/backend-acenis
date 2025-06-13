package com.example.backend.controller;

import com.example.backend.model.Post;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post criarPost(@RequestParam Long idUsuario, @RequestParam String conteudo) {
        return postService.criarPost(idUsuario, conteudo);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Post> listarPostsDeUsuario(@PathVariable Long idUsuario) {
        return postService.listarPostsPorUsuario(idUsuario);
    }

    @GetMapping
    public List<Post> listarTodosPosts() {
        return postService.listarTodos();
    }
}
