package com.example.backend.service;

import com.example.backend.dto.DashboardStatsDTO;
import com.example.backend.repository.AnotacaoRepository;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.EventoAgendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final EventoAgendaRepository eventoRepo;
    private final ClienteRepository clienteRepo;
    private final AnotacaoRepository anotacaoRepo;

    public DashboardService(EventoAgendaRepository eventoRepo, ClienteRepository clienteRepo, AnotacaoRepository anotacaoRepo) {
        this.eventoRepo = eventoRepo;
        this.clienteRepo = clienteRepo;
        this.anotacaoRepo = anotacaoRepo;
    }

    public DashboardStatsDTO getStats(Integer profissionalId) {
        // Usa os métodos de contagem dos repositórios para buscar os dados
        long sessoesHoje = eventoRepo.countByProfissionalIdUserAndDataEvento(profissionalId, LocalDate.now());
        long clientesAtivos = clienteRepo.countByProfissionalIdUser(profissionalId);
        long anotacoes = anotacaoRepo.countByProfissionalIdUser(profissionalId);
        long recursos = 0;

        return new DashboardStatsDTO(sessoesHoje, clientesAtivos, anotacoes, recursos);
    }
}