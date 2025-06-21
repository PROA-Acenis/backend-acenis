package com.example.backend.model;

import jakarta.persistence.*;

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

    public Follow() {}

    public Follow(Usuario follower, Usuario followed) {
        this.follower = follower;
        this.followed = followed;
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
}