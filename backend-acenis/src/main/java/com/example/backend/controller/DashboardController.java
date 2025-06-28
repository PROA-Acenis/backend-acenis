package com.example.backend.controller;

import com.example.backend.repository.AnotacaoRepository;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.EventoAgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final ClienteRepository clienteRepo;
    private final AnotacaoRepository anotacaoRepo;
    private final EventoAgendaRepository eventoRepo;

    public DashboardController(ClienteRepository cr, AnotacaoRepository ar, EventoAgendaRepository er) {
        this.clienteRepo = cr;
        this.anotacaoRepo = ar;
        this.eventoRepo = er;
    }

    // Endpoint para os cards da Visão Geral
    @GetMapping("/stats/{idProfissional}")
    public ResponseEntity<Map<String, Long>> getStats(@PathVariable Integer idProfissional) {
        System.out.println("DEBUG: Buscando estatísticas para o profissional ID: " + idProfissional);
        long sessoesHoje = eventoRepo.countByProfissionalIdUserAndDataEvento(idProfissional, LocalDate.now());
        long clientesAtivos = clienteRepo.countByProfissionalIdUser(idProfissional);
        long anotacoesTotais = anotacaoRepo.countByProfissionalIdUser(idProfissional);
        // Supondo que "recursos" é um valor estático por enquanto
        long recursos = 5;

        Map<String, Long> stats = Map.of(
                "sessoesHoje", sessoesHoje,
                "clientesAtivos", clientesAtivos,
                "anotacoes", anotacoesTotais,
                "recursos", recursos
        );
        System.out.println("DEBUG: Estatísticas encontradas: " + stats);
        return ResponseEntity.ok(stats);
    }
}