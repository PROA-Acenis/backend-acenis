package com.example.backend.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VisaoGeralDTO {
    private BigDecimal saldoDisponivel;
    private BigDecimal saldoPendente;
    private int crescimentoPercentual;
    private long totalClientes;
    private List<Map<String, Object>> vendasMensais;
    private List<VendaRecenteDTO> vendasRecentes;

    public BigDecimal getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public BigDecimal getSaldoPendente() {
        return saldoPendente;
    }

    public void setSaldoPendente(BigDecimal saldoPendente) {
        this.saldoPendente = saldoPendente;
    }

    public int getCrescimentoPercentual() {
        return crescimentoPercentual;
    }

    public void setCrescimentoPercentual(int crescimentoPercentual) {
        this.crescimentoPercentual = crescimentoPercentual;
    }

    public long getTotalClientes() {
        return totalClientes;
    }

    public void setTotalClientes(long totalClientes) {
        this.totalClientes = totalClientes;
    }

    public List<Map<String, Object>> getVendasMensais() {
        return vendasMensais;
    }

    public void setVendasMensais(List<Map<String, Object>> vendasMensais) {
        this.vendasMensais = vendasMensais;
    }

    public List<VendaRecenteDTO> getVendasRecentes() {
        return vendasRecentes;
    }

    public void setVendasRecentes(List<VendaRecenteDTO> vendasRecentes) {
        this.vendasRecentes = vendasRecentes;
    }
}