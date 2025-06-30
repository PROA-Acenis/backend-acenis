package com.example.backend.dto;

import com.example.backend.model.Post;
import com.example.backend.model.Usuario;

import java.time.LocalDateTime;

public class PostResponse {
    private Integer id;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private AuthorResponse autor;
    private boolean likedByUser;
    private long likesCount;
    private long commentsCount;
    private boolean isFollowingAuthor;

    public PostResponse(
            Integer id,
            String conteudo,
            LocalDateTime dataCriacao,
            Usuario autor,
            Long likesCount,
            Long commentsCount,
            Boolean likedByUser
    ) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.autor = new AuthorResponse(autor);
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
        this.likedByUser = likedByUser;
    }

    public PostResponse(Post post, Long likesCount, Long commentsCount, Boolean likedByUser) {
        this.id = post.getId();
        this.conteudo = post.getConteudo();
        this.dataCriacao = post.getDataCriacao();
        this.autor = new AuthorResponse(post.getAutor());
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
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

    public AuthorResponse getAutor() {
        return autor;
    }

    public void setAutor(AuthorResponse autor) {
        this.autor = autor;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public long getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(long commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public boolean getIsFollowingAuthor() {
        return isFollowingAuthor;
    }

    public void setIsFollowingAuthor(boolean isFollowingAuthor) {
        this.isFollowingAuthor = isFollowingAuthor;
    }
}