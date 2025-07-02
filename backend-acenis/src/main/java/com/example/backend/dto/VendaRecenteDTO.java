package com.example.backend.dto;

import java.math.BigDecimal;

public class VendaRecenteDTO {
    private String nomeCliente;
    private BigDecimal valor;
    private String diasAtras;

    public VendaRecenteDTO(String nomeCliente, BigDecimal valor, String diasAtras) {
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.diasAtras = diasAtras;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDiasAtras() {
        return diasAtras;
    }

    public void setDiasAtras(String diasAtras) {
        this.diasAtras = diasAtras;
    }
}