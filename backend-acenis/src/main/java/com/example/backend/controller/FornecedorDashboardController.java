package com.example.backend.controller;

import com.example.backend.dto.ProdutoDTO;
import com.example.backend.dto.VisaoGeralDTO;
import com.example.backend.dto.PedidoFornecedorDTO;
import com.example.backend.model.Produto;
import com.example.backend.service.FornecedorProdutoService;
import com.example.backend.service.FornecedorDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedor-dashboard")
@CrossOrigin(origins = {"http://localhost:5173", "https://front-end-acenis2.vercel.app"})
public class FornecedorDashboardController {

    private final FornecedorProdutoService produtoService;
    private final FornecedorDashboardService dashboardService;

    public FornecedorDashboardController(FornecedorProdutoService produtoService, FornecedorDashboardService dashboardService) {
        this.produtoService = produtoService;
        this.dashboardService = dashboardService;
    }


    @GetMapping("/produtos/{idFornecedor}")
    public ResponseEntity<List<Produto>> getMeusProdutos(@PathVariable Integer idFornecedor) {
        System.out.println("CONTROLLER: Recebida requisição para listar produtos do fornecedor ID: " + idFornecedor);
        List<Produto> produtos = produtoService.listarProdutosPorFornecedor(idFornecedor);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/produtos/{idFornecedor}")
    public ResponseEntity<Produto> criarProduto(@PathVariable Integer idFornecedor, @RequestBody ProdutoDTO produtoDTO) {
        System.out.println("CONTROLLER: Recebida requisição para criar produto para o fornecedor ID: " + idFornecedor);
        Produto novoProduto = produtoService.criarProduto(idFornecedor, produtoDTO);
        return ResponseEntity.ok(novoProduto);
    }

    @DeleteMapping("/produtos/{produtoId}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer produtoId) {
        System.out.println("CONTROLLER: Recebida requisição para deletar produto ID: " + produtoId);
        produtoService.deletarProduto(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedidos/{idFornecedor}")
    public ResponseEntity<List<PedidoFornecedorDTO>> getMeusPedidos(@PathVariable Integer idFornecedor) {
        System.out.println("CONTROLLER: Recebida requisição para listar pedidos do fornecedor ID: " + idFornecedor);
        List<PedidoFornecedorDTO> pedidos = dashboardService.findPedidosPorFornecedor(idFornecedor);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/visao-geral/{idFornecedor}")
    public ResponseEntity<VisaoGeralDTO> getVisaoGeral(@PathVariable Integer idFornecedor) {
        System.out.println("CONTROLLER: Recebida requisição para a visão geral do fornecedor ID: " + idFornecedor);
        VisaoGeralDTO dados = dashboardService.getVisaoGeral(idFornecedor);
        return ResponseEntity.ok(dados);
    }
}