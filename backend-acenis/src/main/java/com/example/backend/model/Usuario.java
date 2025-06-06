package com.example.backend.model;

import jakarta.persistence.*;

@Table(name = "tb_user")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "name_user")
    private String nameUser;

    @Column(name = "email_user", unique = true)
    private String emailUser;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "job")
    private String job;

    @Column(name = "register")
    private String register;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
}