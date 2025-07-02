package com.example.backend.service;

import com.example.backend.dto.ProdutoDTO;
import com.example.backend.model.Categoria;
import com.example.backend.model.Produto;
import com.example.backend.model.Usuario;
import com.example.backend.repository.CategoriaRepository;
import com.example.backend.repository.ProdutoRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FornecedorProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public FornecedorProdutoService(ProdutoRepository pr, UsuarioRepository ur, CategoriaRepository cr) {
        this.produtoRepository = pr;
        this.usuarioRepository = ur;
        this.categoriaRepository = cr;
    }

    public List<Produto> listarProdutosPorFornecedor(Integer fornecedorId) {
        System.out.println("SERVICE LOG: Buscando produtos para o fornecedor ID: " + fornecedorId);
        return produtoRepository.findByFornecedorIdUser(fornecedorId);
    }

    public Produto criarProduto(Integer fornecedorId, ProdutoDTO dto) {
        System.out.println("SERVICE LOG: Iniciando criação de produto para fornecedor ID: " + fornecedorId);

        Usuario fornecedor = usuarioRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor com ID " + fornecedorId + " não encontrado."));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + dto.getCategoriaId() + " não encontrada."));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());
        produto.setDimensoes(dto.getDimensoes());
        produto.setFaixaEtaria(dto.getFaixaEtaria());
        produto.setGenero(dto.getGenero());
        produto.setDesconto(dto.getDesconto());
        produto.setTipoDesconto(dto.getTipoDesconto());
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);
        produto.setImagem("placeholder.png");

        Produto produtoSalvo = produtoRepository.save(produto);
        System.out.println("SERVICE LOG: Produto '" + produtoSalvo.getNome() + "' salvo com sucesso.");
        return produtoSalvo;
    }

    public void deletarProduto(Integer produtoId) {
        System.out.println("SERVICE LOG: Deletando produto ID: " + produtoId);
        if (!produtoRepository.existsById(produtoId)) {
            throw new RuntimeException("Produto com ID " + produtoId + " não encontrado para exclusão.");
        }
        produtoRepository.deleteById(produtoId);
        System.out.println("SERVICE LOG: Produto ID: " + produtoId + " deletado com sucesso.");
    }
}