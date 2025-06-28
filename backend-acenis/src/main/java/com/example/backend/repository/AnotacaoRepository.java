package com.example.backend.repository;

import com.example.backend.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Integer> {
    // Busca anotações pelo ID do profissional, ordenadas pela mais recente
    List<Anotacao> findByProfissionalIdUserOrderByDataCriacaoDesc(Integer profissionalId);
    // Conta anotações pelo ID do profissional
    long countByProfissionalIdUser(Integer profissionalId);
}