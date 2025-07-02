package com.example.backend.repository;

import com.example.backend.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<ItemPedido, Integer> {

    /**
     * Esta é uma query customizada que busca todos os itens de pedido (order items)
     * que pertencem a produtos de um fornecedor específico.
     *
     * Como funciona:
     * 1. "FROM ItemPedido oi": Seleciona a partir da nossa entidade ItemPedido.
     * 2. "JOIN oi.produto p": Faz a junção com a entidade Produto, que está dentro de ItemPedido.
     * 3. "JOIN p.fornecedor f": Faz a junção com a entidade Usuario (que chamamos de 'fornecedor'), que está dentro de Produto.
     * 4. "WHERE f.idUser = :fornecedorId": Filtra o resultado, trazendo apenas os itens onde o ID do fornecedor
     * é igual ao parâmetro que recebemos.
     */
    @Query("SELECT oi FROM ItemPedido oi JOIN oi.produto p JOIN p.fornecedor f WHERE f.idUser = :fornecedorId")
    List<ItemPedido> findOrderItemsByFornecedorId(@Param("fornecedorId") Integer fornecedorId);

}