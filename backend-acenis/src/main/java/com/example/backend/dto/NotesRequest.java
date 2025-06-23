package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NotesRequest {

    @NotBlank(message = "O título da nota não pode estar em branco")
    @Size(max = 100, message = "O título da nota deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "O conteúdo da nota não pode estar em branco")
    private String content;

    public NotesRequest(){}

    public NotesRequest(String titulo, String content){
        this.titulo = titulo;
        this.content = content;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}