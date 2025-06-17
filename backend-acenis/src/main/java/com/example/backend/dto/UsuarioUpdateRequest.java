package com.example.backend.dto;

public class UsuarioUpdateRequest {

    private String job;
    private String register;
    private String passwordUser;
    private String nameUser;
    private String emailUser;

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
}