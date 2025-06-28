package com.example.backend.repository;

import com.example.backend.model.EventoAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoAgendaRepository extends JpaRepository<EventoAgenda, Integer> {
    // Busca eventos pelo ID do profissional
    List<EventoAgenda> findByProfissionalIdUser(Integer profissionalId);
    // Conta eventos do profissional em uma data específica
    long countByProfissionalIdUserAndDataEvento(Integer profissionalId, LocalDate data);
}