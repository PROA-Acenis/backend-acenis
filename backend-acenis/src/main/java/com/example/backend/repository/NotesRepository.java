package com.example.backend.repository;

import com.example.backend.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

    List<Notes> findByProfissionalIdUser(Integer profissionalId);

    Optional<Notes> findByIdAndProfissionalIdUser(Integer id, Integer profissionalId);
}