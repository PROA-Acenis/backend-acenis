package com.example.backend.dto;

public class ClienteDTO {
    private String nomeCliente;
    private String nomeResponsavel;
    private String contatoResponsavel;

    // Getters e Setters
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }
    public String getContatoResponsavel() { return contatoResponsavel; }
    public void setContatoResponsavel(String contatoResponsavel) { this.contatoResponsavel = contatoResponsavel; }
}