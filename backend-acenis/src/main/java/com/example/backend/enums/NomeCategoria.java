package com.example.backend.model.enums;

public enum NomeCategoria {
    QUEBRA_CABECA("quebra-cabeça"),
    JOGO_DA_MEMORIA("jogo-da-memória");

    private String descricao;

    NomeCategoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}