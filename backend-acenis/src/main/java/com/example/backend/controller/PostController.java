package com.example.backend.controller;

import com.example.backend.model.Post;
import com.example.backend.dto.PostRequest;
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
    public Post criarPost(@RequestBody PostRequest postRequest) {
        Integer idUser = postRequest.getIdUser();
        String conteudo = postRequest.getConteudo();
        return postService.criarPost(idUser, conteudo);
    }

    @GetMapping("/usuario/{idUser}")
    public List<Post> listarPostsDeUsuario(@PathVariable Integer idUser) {
        return postService.listarPostsPorUsuario(idUser);
    }


    @GetMapping
    public List<Post> listarTodosPosts() {
        return postService.listarTodos();
    }
}
