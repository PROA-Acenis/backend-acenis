package com.example.backend.dto;

public class LoginResponse {
    private String nome;
    private String tipo; // O tipo será "PROFISSIONAL" ou "RESPONSAVEL"

    public LoginResponse(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
}