package com.example.backend.dto;

import com.example.backend.model.TipoUsuario;

public class LoginResponse {
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private TipoUsuario tipo;

    public LoginResponse(Integer idUser, String nameUser, String emailUser, TipoUsuario tipo) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.tipo = tipo;
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