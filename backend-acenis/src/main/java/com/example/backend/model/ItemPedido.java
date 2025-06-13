package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_orderitem")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderitem")
    private Integer id;

    @Column(name = "quantity_orderitem", nullable = false)
    private Integer quantidade;

    @Column(name = "uni_price_orderitem", nullable = false)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_order") // FK 'id_order' na tabela tb_orderitem
    @JsonIgnore
    private Pedido pedido;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
}