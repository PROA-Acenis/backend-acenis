package com.example.backend.repository;

import com.example.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Busca clientes pelo ID do profissional
    List<Cliente> findByProfissionalIdUser(Integer profissionalId);
    // Conta clientes pelo ID do profissional
    long countByProfissionalIdUser(Integer profissionalId);
}