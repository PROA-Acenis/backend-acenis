package com.example.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoFornecedorDTO {
    private Integer idPedido;
    private LocalDateTime data;
    private String clienteNome;
    private int totalItens;
    private BigDecimal totalValor;
    private String status;
    private String pagamento;

    public PedidoFornecedorDTO(Integer id, LocalDateTime data, String clienteNome, int totalItens, BigDecimal totalValor, String status, String pagamento) {
        this.idPedido = id;
        this.data = data;
        this.clienteNome = clienteNome;
        this.totalItens = totalItens;
        this.totalValor = totalValor;
        this.status = status;
        this.pagamento = pagamento;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public int getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(int totalItens) {
        this.totalItens = totalItens;
    }

    public BigDecimal getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(BigDecimal totalValor) {
        this.totalValor = totalValor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
}