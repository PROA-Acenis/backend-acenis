package com.example.backend.dto;

public class DashboardStatsDTO {
    private long sessoesHoje;
    private long clientesAtivos;
    private long anotacoesTotais;
    private long recursos;

    public DashboardStatsDTO(long sessoesHoje, long clientesAtivos, long anotacoesTotais, long recursos) {
        this.sessoesHoje = sessoesHoje;
        this.clientesAtivos = clientesAtivos;
        this.anotacoesTotais = anotacoesTotais;
        this.recursos = recursos;
    }

    public long getSessoesHoje() { return sessoesHoje; }
    public long getClientesAtivos() { return clientesAtivos; }
    public long getAnotacoesTotais() { return anotacoesTotais; }
    public long getRecursos() { return recursos; }
}