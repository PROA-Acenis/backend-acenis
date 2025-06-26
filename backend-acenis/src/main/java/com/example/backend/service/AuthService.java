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
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailUser(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (loginRequest.getSenha().equals(usuario.getPasswordUser())) {
                String token = jwtService.generateToken(usuario.getIdUser());

                return new LoginResponse(
                        token,
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