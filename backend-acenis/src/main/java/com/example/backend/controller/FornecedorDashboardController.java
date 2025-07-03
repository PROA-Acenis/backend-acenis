package com.example.backend.controller;

import com.example.backend.dto.ProdutoDTO;
import com.example.backend.model.Produto;
import com.example.backend.model.Usuario;
import com.example.backend.service.FornecedorProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedor-dashboard")
@CrossOrigin(origins = {"http://localhost:5173", "https://front-end-acenis2.vercel.app", "https://www.acenis.com.br"})
public class FornecedorDashboardController {

    private final FornecedorProdutoService produtoService;

    public FornecedorDashboardController(FornecedorProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getMeusProdutos(@AuthenticationPrincipal UserDetails userDetails) {
        Integer fornecedorId = ((Usuario) userDetails).getIdUser();
        return ResponseEntity.ok(produtoService.listarProdutosPorFornecedor(fornecedorId));
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> criarProduto(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ProdutoDTO produtoDTO) {
        Integer fornecedorId = ((Usuario) userDetails).getIdUser();
        return ResponseEntity.ok(produtoService.criarProduto(fornecedorId, produtoDTO));
    }

    @DeleteMapping("/produtos/{produtoId}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer produtoId, @AuthenticationPrincipal UserDetails userDetails) {
        Integer fornecedorId = ((Usuario) userDetails).getIdUser();

        produtoService.deletarProduto(produtoId, fornecedorId);

        return ResponseEntity.noContent().build();
    }
}