package com.example.backend.model.enums;

public enum NomeCategoria {
    QUEBRA_CABECA("quebra-cabeça"),
    JOGO_DA_MEMORIA("jogo-da-memória");

    private final String descricao;

    NomeCategoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para encontrar o enum a partir do texto do banco
    public static NomeCategoria fromDescricao(String texto) {
        for (NomeCategoria categoria : NomeCategoria.values()) {
            if (categoria.descricao.equalsIgnoreCase(texto)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria desconhecida: " + texto);
    }
}