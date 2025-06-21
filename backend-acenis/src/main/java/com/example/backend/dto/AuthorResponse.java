package com.example.backend.dto;

import com.example.backend.model.Usuario;

public class AuthorResponse {
    private Integer id;
    private String nameUser;
    private String emailUser;
    private String profilePic;
    private String job;
    private Long followersCount;
    private Boolean isFollowingAuthor;

    public AuthorResponse(Usuario usuario) {
        this.id = usuario.getIdUser();
        this.nameUser = usuario.getNameUser();
        this.emailUser = usuario.getEmailUser();
        this.profilePic = usuario.getProfilePic();
        this.job = usuario.getJob();
        this.followersCount = 0L;
        this.isFollowingAuthor = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.emailUser = emailUser;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public Boolean getIsFollowingAuthor() {
        return isFollowingAuthor;
    }

    public void setIsFollowingAuthor(Boolean isFollowingAuthor) {
        this.isFollowingAuthor = isFollowingAuthor;
    }
}