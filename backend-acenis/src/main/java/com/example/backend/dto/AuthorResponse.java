package com.example.backend.dto;

import com.example.backend.model.Usuario;
import com.example.backend.model.TipoUsuario;

public class AuthorResponse {
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private String tipo;
    private String profilePic;
    private Long followersCount;
    private Boolean isFollowingAuthor;

    public AuthorResponse(Usuario usuario) {
        if (usuario != null) {
            this.idUser = usuario.getIdUser();
            this.nameUser = usuario.getNameUser();
            this.emailUser = usuario.getEmailUser();
            this.tipo = usuario.getTipo() != null ? usuario.getTipo().name() : null;
            this.profilePic = usuario.getProfilePic();
        }
    }

    public AuthorResponse() {
    }

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
        this.emailUser = emailUser;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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