package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_anotacao")
public class Anotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnotacao;

    private String tituloAnotacao;

    @Lob
    private String textoAnotacao;

    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profissional", nullable = false)
    @JsonIgnore
    private Usuario profissional;

    // --- GETTERS E SETTERS COMPLETOS (o que estava faltando) ---
    public Integer getIdAnotacao() { return idAnotacao; }
    public void setIdAnotacao(Integer idAnotacao) { this.idAnotacao = idAnotacao; }
    public String getTitulo() { return tituloAnotacao; }
    public void setTitulo(String titulo) { this.tituloAnotacao = titulo; }
    public String getTexto() { return textoAnotacao; }
    public void setTexto(String texto) { this.textoAnotacao = texto; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Usuario getProfissional() { return profissional; }
    public void setProfissional(Usuario profissional) { this.profissional = profissional; }
}