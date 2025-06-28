package com.example.backend.controller;

import com.example.backend.dto.AnotacaoDTO;
import com.example.backend.model.Anotacao;
import com.example.backend.service.AnotacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/anotacoes")
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    public AnotacaoController(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService;
    }

    @PostMapping
    public ResponseEntity<Anotacao> criarAnotacao(@RequestBody AnotacaoDTO anotacaoDTO) {
        System.out.println("DEBUG: Controller - Recebida requisição para criar anotação.");
        return ResponseEntity.ok(anotacaoService.criarAnotacao(anotacaoDTO));
    }

    @GetMapping("/profissional/{idProfissional}")
    public ResponseEntity<List<Anotacao>> listarPorProfissional(@PathVariable Integer idProfissional) {
        System.out.println("DEBUG: Controller - Buscando anotações para o profissional ID: " + idProfissional);
        return ResponseEntity.ok(anotacaoService.listarPorProfissional(idProfissional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnotacao(@PathVariable Integer id) {
        System.out.println("DEBUG: Controller - Deletando anotação ID: " + id);
        anotacaoService.deletarAnotacao(id);
        return ResponseEntity.noContent().build();
    }
}