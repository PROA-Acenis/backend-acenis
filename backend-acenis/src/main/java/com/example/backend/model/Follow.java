package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_followers", uniqueConstraints = @UniqueConstraint(columnNames = {"id_follower", "id_following"}))
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_followers")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_follower", nullable = false)
    private Usuario follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_following", nullable = false)
    private Usuario followed;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Follow() {
        this.createdAt = LocalDateTime.now();
    }

    public Follow(Usuario follower, Usuario followed) {
        this.follower = follower;
        this.followed = followed;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getFollower() {
        return follower;
    }

    public void setFollower(Usuario follower) {
        this.follower = follower;
    }

    public Usuario getFollowed() {
        return followed;
    }

    public void setFollowed(Usuario followed) {
        this.followed = followed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}