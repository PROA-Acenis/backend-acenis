package com.example.backend.dto;

import java.util.List;

public class PedidoRequestDTO {
    private Integer usuarioId;
    private List<ItemPedidoRequestDTO> itens;
    // Adicione aqui outros campos se vierem do formulário 'dados.jsx', ex: private String cep;

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public List<ItemPedidoRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoRequestDTO> itens) { this.itens = itens; }
}