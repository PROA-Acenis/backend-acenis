package com.example.backend.controller;

import com.example.backend.dto.EventoDTO;
import com.example.backend.model.EventoAgenda;
import com.example.backend.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
@CrossOrigin(origins = {"http://localhost:5173", "https://front-end-acenis2.vercel.app"})
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("/eventos")
    public ResponseEntity<EventoAgenda> criarEvento(@RequestBody EventoDTO eventoDTO) {
        System.out.println("DEBUG: Controller - Recebida requisição para criar evento na agenda.");
        return ResponseEntity.ok(agendaService.criarEvento(eventoDTO));
    }

    @GetMapping("/eventos/profissional/{idProfissional}")
    public ResponseEntity<List<EventoAgenda>> listarEventos(@PathVariable Integer idProfissional) {
        System.out.println("DEBUG: Controller - Buscando eventos para o profissional ID: " + idProfissional);
        return ResponseEntity.ok(agendaService.listarPorProfissional(idProfissional));
    }

    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Integer id) {
        System.out.println("DEBUG: Controller - Deletando evento ID: " + id);
        agendaService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }
}