package com.example.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoDTO {
    private LocalDate dataEvento;
    private LocalTime horaEvento;
    private String tipoEvento;
    private String cor;
    private Integer clienteId;
    private Integer profissionalId;

    // Getters e Setters
    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }
    public LocalTime getHoraEvento() { return horaEvento; }
    public void setHoraEvento(LocalTime horaEvento) { this.horaEvento = horaEvento; }
    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public Integer getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Integer profissionalId) { this.profissionalId = profissionalId; }
}