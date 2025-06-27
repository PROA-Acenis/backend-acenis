package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_follower", nullable = false)
    private Usuario follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_followed", nullable = false)
    private Usuario followed;

    @Column(name = "follow_date", nullable = false) // Mapeia o campo para a coluna no banco
    private LocalDateTime followDate; // Adiciona o campo para a data de follow

    public Follow() {}

    public Follow(Usuario follower, Usuario followed) {
        this.follower = follower;
        this.followed = followed;
        this.followDate = LocalDateTime.now();
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

    public LocalDateTime getFollowDate() { return followDate; }

    public void setFollowDate(LocalDateTime followDate) { this.followDate = followDate; }
}