package com.example.backend.model;

import com.example.backend.model.enums.NomeCategoria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer id;

    @Column(name = "name_category", nullable = false)
    private NomeCategoria nome;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public NomeCategoria getNome() { return nome; }
    public void setNome(NomeCategoria nome) { this.nome = nome; }
}