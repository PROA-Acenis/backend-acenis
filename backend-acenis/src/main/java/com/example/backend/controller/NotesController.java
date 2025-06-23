package com.example.backend.controller;

import com.example.backend.dto.NotesRequest;
import com.example.backend.dto.NotesResponse;
import com.example.backend.model.Notes;
import com.example.backend.model.Usuario;
import com.example.backend.service.NotesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping
    public ResponseEntity <NotesResponse> criarNota(
            @Valid @RequestBody NotesRequest notesRequest,
            @AuthenticationPrincipal Usuario profissionalLogado
    ) {
        Notes notes = new Notes();
        notes.setTitulo(notesRequest.getTitulo());
        notes.setContent(notesRequest.getContent());
        notes.setProfissional(profissionalLogado);

        Notes newNotes = notesService.criarNota(notes, profissionalLogado.getIdUser());
        return new ResponseEntity<>(NotesResponse.fromEntity(newNotes), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotesResponse>> getTodasNotasPorProfissional(
            @AuthenticationPrincipal Usuario profissionalLogado
    ) {
        List<Notes> notes = notesService.buscarTodasNotasPorProfissional(profissionalLogado.getIdUser());
        List<NotesResponse> notesResponse = notes.stream()
                .map(NotesResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotesResponse> getNotaPorId(
            @PathVariable Integer id,
            @AuthenticationPrincipal Usuario profissionalLogado
    ) {
        return notesService.buscarNotaPorIdEProfissional(id, profissionalLogado.getIdUser())
                .map(NotesResponse::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotesResponse> atualizarNota (
            @PathVariable Integer id,
            @Valid @RequestBody NotesRequest notesRequest,
            @AuthenticationPrincipal Usuario profissionalLogado
    ) {
        try {
            Notes notesParaAtualizar = new Notes();
            notesParaAtualizar.setTitulo(notesRequest.getTitulo());
            notesParaAtualizar.setContent(notesRequest.getContent());

            Notes notaAtualizada = notesService.atualizarNota(id, notesParaAtualizar, profissionalLogado.getIdUser());
            return ResponseEntity.ok(NotesResponse.fromEntity(notaAtualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(
            @PathVariable Integer id,
            @AuthenticationPrincipal Usuario profissionalLogado
    ) {
        try {
            notesService.deletarNota(id, profissionalLogado.getIdUser());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}