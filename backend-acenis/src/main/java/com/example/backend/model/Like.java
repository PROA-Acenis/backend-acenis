package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.backend.model.Usuario;

@Entity
@Table(name = "tb_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_like")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario user;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Like() {
    }

    public Like(Post post, Usuario user) {
        this.post = post;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
