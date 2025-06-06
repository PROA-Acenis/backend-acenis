package com.example.cadastroprofissionais.controller;

import com.example.cadastroprofissionais.model.UsuarioProfissional;
import com.example.cadastroprofissionais.repository.UsuarioProfissionalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // para permitir o front acessar
@RestController
@RequestMapping("/usuariosProfissional")
public class UsuarioProfissionalController {

    private final UsuarioProfissionalRepository usuarioProfissionalRepository;

    public UsuarioProfissionalController(UsuarioProfissionalRepository usuarioProfissionalRepository) {
        this.usuarioProfissionalRepository = usuarioProfissionalRepository;
    }

    @PostMapping
    public UsuarioProfissional cadastrar(@RequestBody UsuarioProfissional usuarioprofisional) {
        if (usuarioProfissionalRepository.existsByEmail(usuarioprofisional.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }
        return usuarioProfissionalRepository.save(usuarioprofisional);
    }

    @GetMapping
    public List<UsuarioProfissional> listar() {
        return usuarioProfissionalRepository.findAll();
    }
}
