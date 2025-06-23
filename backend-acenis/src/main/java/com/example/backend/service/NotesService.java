package com.example.backend.service;

import com.example.backend.model.Notes;
import com.example.backend.model.Usuario;
import com.example.backend.repository.NotesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Transactional
    public Notes criarNota(Notes notes, Integer profissionalId) {
        return notesRepository.save(notes);
    }

    public List<Notes> buscarTodasNotasPorProfissional(Integer profissionalId) {
        return notesRepository.findByProfissionalIdUser(profissionalId);
    }

    public Optional<Notes> buscarNotaPorIdEProfissional(Integer id, Integer profissionalId) {
        return notesRepository.findByIdAndProfissionalIdUser(id, profissionalId);
    }

    @Transactional
    public Notes atualizarNota(Integer id, Notes notesAtualizada, Integer profissionalId) {
        return notesRepository.findByIdAndProfissionalIdUser(id, profissionalId)
                .map(noteExistente -> {
                    if (notesAtualizada.getTitulo() != null) {
                        noteExistente.setTitulo(notesAtualizada.getTitulo());
                    }
                    if (notesAtualizada.getContent() != null) {
                        noteExistente.setContent(notesAtualizada.getContent());
                    }
                    return notesRepository.save(noteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Nota não encontrada ou não pertence a este profissional."));
    }

    @Transactional
    public void deletarNota(Integer id, Integer profissionalId) {
        Optional<Notes> notesOptional = notesRepository.findByIdAndProfissionalIdUser(id, profissionalId);
        if (notesOptional.isPresent()) {
            notesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Nota não encontrada ou não pertence a este profissional.");
        }
    }
}