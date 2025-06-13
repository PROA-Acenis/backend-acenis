package com.example.backend.repository;

import com.example.backend.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interface nos dá todos os métodos básicos de CRUD para a entidade ItemPedido.
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}