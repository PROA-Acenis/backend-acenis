package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Interger id;

    @Column(name = "content_post", nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "creation_date_post", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario autor;
}
