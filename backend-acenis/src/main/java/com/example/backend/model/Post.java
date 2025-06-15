package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Importação necessária

@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Integer id; // MANTIDO: ID como Integer

    @Column(name = "content_post", nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "creation_date_post", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER) // ADICIONADO: FetchType.EAGER para carregar o autor junto com o post
    @JoinColumn(name = "id_user", nullable = false) // Coluna de chave estrangeira que aponta para o ID do usuário
    private Usuario autor; // MANTIDO: Nome do campo 'autor'

    // Construtor padrão (necessário para JPA)
    public Post() {
    }

    // ADICIONADO: Construtor com campos necessários para criar um Post
    public Post(String conteudo, Usuario autor) {
        this.conteudo = conteudo;
        this.autor = autor;
        this.dataCriacao = LocalDateTime.now(); // Define a data de criação automaticamente
    }

    // --- Getters e Setters (mantidos com 'Integer id' e 'autor') ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
