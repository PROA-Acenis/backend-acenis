package com.example.backend.dto;

import java.time.LocalDateTime;
import com.example.backend.model.Notes;

public class NotesResponse {
    private Integer id;
    private String titulo;
    private String content;
    private LocalDateTime dateCreate;
    private Integer profissionalId;
    private String profissionalName;

    public NotesResponse() {}

    public NotesResponse(Integer id, String titulo, String content, LocalDateTime dateCreate, Integer profissionalId, String profissionalName){
        this.id = id;
        this.titulo = titulo;
        this.content = content;
        this.dateCreate = dateCreate;
        this.profissionalId = profissionalId;
        this.profissionalName = profissionalName;
    }

    public static NotesResponse fromEntity(Notes notes) {
        return new NotesResponse(
                notes.getId(),
                notes.getTitulo(),
                notes.getContent(),
                notes.getDateCreate(),
                notes.getProfissional().getIdUser(),
                notes.getProfissional().getNameUser()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Integer profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getProfissionalName() {
        return profissionalName;
    }

    public void setProfissionalName(String profissionalName) {
        this.profissionalName = profissionalName;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }
}