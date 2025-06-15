package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Remova a linha abaixo se ela existir no seu código:
// @CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailUser(loginRequest.getEmail());

        if (usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();

            if (loginRequest.getSenha().equals(usuario.getPasswordUser())){
                LoginResponse response = new LoginResponse(usuario.getIdUser(), usuario.getNameUser(), usuario.getTipo().name());
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("E-mail ou senha inválidos.");
    }
}
