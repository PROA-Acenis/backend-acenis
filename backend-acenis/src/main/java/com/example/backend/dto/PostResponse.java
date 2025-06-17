package com.example.backend.dto;

import com.example.backend.model.Post;
import java.time.LocalDateTime;
import java.util.Optional;

public class PostResponse {
    private Integer id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private UsuarioResponse autor;
    private Long likesCount;
    private boolean likedByUser;

    public PostResponse() {
    }

    public static PostResponse fromPost(Post post, Integer currentUserId) {
        if (post == null) {
            return null;
        }

        Long likesCount = (long) (post.getLikes() != null ? post.getLikes().size() : 0);

        boolean likedByUser = false;
        if (currentUserId != null && post.getLikes() != null) {
            likedByUser = post.getLikes().stream()
                    .anyMatch(like -> like.getUser() != null && like.getUser().getIdUser().equals(currentUserId));
        }

        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setConteudo(post.getConteudo());
        response.setDataCriacao(post.getDataCriacao());
        response.setAutor(new UsuarioResponse(post.getAutor()));
        response.setLikesCount(likesCount);
        response.setLikedByUser(likedByUser);

        return response;
    }

    public PostResponse(Post post, Long likesCount, boolean likedByUser) {
        this.id = post.getId();
        this.conteudo = post.getConteudo();
        this.dataCriacao = post.getDataCriacao();
        this.autor = new UsuarioResponse(post.getAutor());
        this.likesCount = likesCount;
        this.likedByUser = likedByUser;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioResponse getAutor() {
        return autor;
    }

    public void setAutor(UsuarioResponse autor) {
        this.autor = autor;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }
}