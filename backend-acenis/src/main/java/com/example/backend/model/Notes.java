package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profissional", referencedColumnName = "id_user", nullable = false) // 'id_user' é a coluna de ID no seu tb_user
    private Usuario profissional;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "date_create", nullable = false, updatable = false)
    private LocalDateTime dateCreate;

    public Notes(){
        this.dateCreate = LocalDateTime.now();
    }

    public Notes(String titulo, String content, Usuario profissional) {
        this.titulo = titulo;
        this.content = content;
        this.profissional = profissional;
        this.dateCreate = LocalDateTime.now();
    }

    // --- Getters e Setters ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}