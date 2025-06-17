package com.example.backend.dto;

import com.example.backend.model.Usuario;
import com.example.backend.model.TipoUsuario;

public class UsuarioResponse {
    private Integer id;
    private String nome;
    private String email;
    private String profilePic;
    private String job;
    private String register;
    private TipoUsuario tipo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Usuario usuario) {
        if (usuario != null) {
            this.id = usuario.getIdUser();
            this.nome = usuario.getNameUser();
            this.email = usuario.getEmailUser();
            this.profilePic = usuario.getProfilePic(); // Mapeia o profilePic
            this.job = usuario.getJob();
            this.register = usuario.getRegister();
            this.tipo = usuario.getTipo();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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