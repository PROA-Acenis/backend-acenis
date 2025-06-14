package com.example.backend.dto;

public class LoginResponse {
    private Integer id;
    private String name;
    private String tipo;

    public LoginResponse(Integer id, String name, String tipo) {
        this.id = id;
        this.name = name;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
