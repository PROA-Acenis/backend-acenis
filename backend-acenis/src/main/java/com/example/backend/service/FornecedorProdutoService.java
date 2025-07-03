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
        return produtoRepository.findByFornecedorIdUser(fornecedorId);
    }

    public Produto criarProduto(Integer fornecedorId, ProdutoDTO dto) {
        Usuario fornecedor = usuarioRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setImagem("placeholder.png"); // Lógica de upload virá depois
        produto.setFaixaEtaria(dto.getFaixaEtaria());
        produto.setEstoque(dto.getEstoque());
        produto.setDimensoes(dto.getDimensoes());
        produto.setGenero(dto.getGenero());
        produto.setDesconto(dto.getDesconto());
        produto.setTipoDesconto(dto.getTipoDesconto());
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer produtoId, Integer fornecedorId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getFornecedor().getIdUser().equals(fornecedorId)) {
            throw new SecurityException("Acesso negado: você não é o dono deste produto.");
        }

        produtoRepository.deleteById(produtoId);
    }
}