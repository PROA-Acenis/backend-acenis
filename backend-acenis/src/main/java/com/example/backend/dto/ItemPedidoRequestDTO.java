package com.example.backend.dto;

public class ItemPedidoRequestDTO {
    private Integer produtoId;
    private Integer quantidade;

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}