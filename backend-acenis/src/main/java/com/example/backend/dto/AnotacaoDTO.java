package com.example.backend.dto;

public class AnotacaoDTO {
    private String titulo;
    private String texto;
    private Integer clienteId;
    private Integer profissionalId;

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public Integer getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Integer profissionalId) { this.profissionalId = profissionalId; }
}