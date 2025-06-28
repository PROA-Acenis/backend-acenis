package com.example.backend.controller;

import com.example.backend.model.Cliente;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteController(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Lista todos os clientes de um profissional
    @GetMapping("/profissional/{idProfissional}")
    public ResponseEntity<List<Cliente>> listarPorProfissional(@PathVariable Integer idProfissional) {
        System.out.println("DEBUG: Buscando clientes para o profissional ID: " + idProfissional);
        return ResponseEntity.ok(clienteRepository.findByProfissionalIdUser(idProfissional));
    }

    // Cria um novo cliente
    @PostMapping("/profissional/{idProfissional}")
    public ResponseEntity<Cliente> criarCliente(@PathVariable Integer idProfissional, @RequestBody Cliente cliente) {
        System.out.println("DEBUG: Criando cliente para o profissional ID: " + idProfissional);
        return usuarioRepository.findById(idProfissional).map(profissional -> {
            cliente.setProfissional(profissional);
            Cliente novoCliente = clienteRepository.save(cliente);
            System.out.println("DEBUG: Cliente criado com sucesso: " + novoCliente.getNomeCliente());
            return ResponseEntity.ok(novoCliente);
        }).orElse(ResponseEntity.notFound().build());
    }
}