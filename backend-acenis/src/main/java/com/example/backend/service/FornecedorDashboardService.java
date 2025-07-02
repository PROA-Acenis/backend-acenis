package com.example.backend.service;

import com.example.backend.dto.VisaoGeralDTO;
import com.example.backend.dto.PedidoFornecedorDTO;
import com.example.backend.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorDashboardService {

    private final OrderItemRepository orderItemRepository;

    public FornecedorDashboardService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<PedidoFornecedorDTO> findPedidosPorFornecedor(Integer fornecedorId) {
        System.out.println("SERVICE: Buscando itens de pedido para o fornecedor ID: " + fornecedorId);
        return orderItemRepository.findOrderItemsByFornecedorId(fornecedorId).stream()
                .collect(Collectors.groupingBy(item -> item.getPedido().getId()))
                .values().stream()
                .map(items -> {
                    var primeiroItem = items.get(0);
                    int totalItens = items.stream().mapToInt(item -> item.getQuantidade()).sum();
                    BigDecimal valorTotal = items.stream()
                            .map(item -> item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return new PedidoFornecedorDTO(
                            primeiroItem.getPedido().getId(),
                            primeiroItem.getPedido().getData().atStartOfDay(),
                            primeiroItem.getPedido().getUsuario().getNameUser(),
                            totalItens,
                            valorTotal,
                            primeiroItem.getPedido().getStatus(),
                            "Pago"
                    );
                }).collect(Collectors.toList());
    }

    public VisaoGeralDTO getVisaoGeral(Integer fornecedorId) {
        System.out.println("SERVICE: Calculando visão geral para o fornecedor ID: " + fornecedorId);
        BigDecimal saldoDisponivel = new BigDecimal("27500.50");
        BigDecimal saldoPendente = new BigDecimal("4500.75");

        // return new VisaoGeralDTO(saldoDisponivel, saldoPendente, ...);
        return null; // Retornando null para evitar erros de compilação sem as outras classes DTO
    }
}