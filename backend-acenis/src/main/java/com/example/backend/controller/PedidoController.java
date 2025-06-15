package com.example.backend.controller;

import com.example.backend.dto.PedidoRequestDTO;
import com.example.backend.model.Pedido;
import com.example.backend.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedidoRequestDTO);
            return ResponseEntity.ok(novoPedido);
        } catch (RuntimeException e) {
            // Em um app real, teríamos tratamentos de erro mais específicos
            return ResponseEntity.badRequest().build();
        }
    }
}