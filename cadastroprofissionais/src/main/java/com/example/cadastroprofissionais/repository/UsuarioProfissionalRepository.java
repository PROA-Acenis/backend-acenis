package com.example.cadastroprofissionais.repository;

import com.example.cadastroprofissionais.model.UsuarioProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioProfissionalRepository extends JpaRepository<UsuarioProfissional, Long> {
    boolean existsByEmail(String email);
}
