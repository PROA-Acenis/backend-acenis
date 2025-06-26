package com.example.backend.dto;

import com.example.backend.model.TipoUsuario;

public class LoginResponse {
    private String token;
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private TipoUsuario tipo;

    public LoginResponse(String token, Integer idUser, String nameUser, String emailUser, TipoUsuario tipo) {
        this.token = token;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}