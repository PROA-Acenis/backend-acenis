package com.example.backend.service;

import com.example.backend.dto.PedidoRequestDTO;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Pedido criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        // 1. Encontrar o usuário
        Usuario usuario = usuarioRepository.findById(pedidoRequestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Criar o objeto Pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setData(LocalDate.now());
        pedido.setStatus("Finalizado");

        // 3. Processar os itens do pedido
        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal totalPedido = BigDecimal.ZERO;

        for (var itemDTO : pedidoRequestDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            itemPedido.setPedido(pedido);

            itens.add(itemPedido);

            // Calcula o subtotal do item e adiciona ao total do pedido
            totalPedido = totalPedido.add(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));
        }

        pedido.setItens(itens);
        pedido.setTotal(totalPedido);

        return pedidoRepository.save(pedido);
    }
}