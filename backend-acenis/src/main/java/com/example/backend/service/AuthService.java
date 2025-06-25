package com.example.backend.service;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailUser(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (loginRequest.getSenha().equals(usuario.getPasswordUser())) {
                return new LoginResponse(
                        usuario.getIdUser(),
                        usuario.getNameUser(),
                        usuario.getEmailUser(),
                        usuario.getTipo()
                );
            }
        }

        throw new RuntimeException("E-mail ou senha inválidos.");
    }
}