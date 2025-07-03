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
        produto.setImagem("placeholder.png"); // Lógica de upload de imagem virá no futuro

        Produto produtoSalvo = produtoRepository.save(produto);
        System.out.println("SERVICE LOG: Produto '" + produtoSalvo.getNome() + "' salvo com sucesso.");
        return produtoSalvo;
    }

    public void deletarProduto(Integer produtoId, Integer fornecedorId) {
        System.out.println("SERVICE LOG: Tentando deletar produto ID: " + produtoId + " pelo fornecedor ID: " + fornecedorId);
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!produto.getFornecedor().getIdUser().equals(fornecedorId)) {
            throw new SecurityException("Acesso negado: você não é o dono deste produto.");
        }

        produtoRepository.deleteById(produtoId);
        System.out.println("SERVICE LOG: Produto deletado com sucesso.");
    }
}