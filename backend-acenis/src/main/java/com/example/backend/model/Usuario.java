package com.example.backend.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name = "tb_user")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "email_user", unique = true)
    private String emailUser;

    @JsonIgnore
    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "job")
    private String job;

    @Column(name = "register")
    private String register;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @Column(name = "profile_pic")
    private String profilePic;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("user_posts")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("user_comments")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("user_likes")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("follower_follows")
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("followed_follows")
    private List<Follow> followers = new ArrayList<>();



    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        if (emailUser != null) {
            this.emailUser = emailUser.trim().toLowerCase();
        } else {
            this.emailUser = null;
        }
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follow> following) {
        this.following = following;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setAutor(this);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.setAutor(null);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setUsuario(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setUsuario(null);
    }

    public void addLike(Like like) {
        this.likes.add(like);
        like.setUser(this);
    }

    public void removeLike(Like like) {
        this.likes.remove(like);
        like.setUser(null);
    }

    public void addFollowing(Follow follow) {
        this.following.add(follow);
        follow.setFollower(this);
    }

    public void removeFollowing(Follow follow) {
        this.following.remove(follow);
        follow.setFollower(null);
    }

    public void addFollower(Follow follow) {
        this.followers.add(follow);
        follow.setFollowed(this);
    }

    public void removeFollower(Follow follow) {
        this.followers.remove(follow);
        follow.setFollowed(null);
    }
}