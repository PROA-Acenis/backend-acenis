package com.example.backend.repository;

import com.example.backend.model.TipoUsuario;
import com.example.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmailUser(String emailUser);

    Optional<Usuario> findByEmailUser(String emailUser);

    List<Usuario> findByTipo(TipoUsuario tipo);
}
