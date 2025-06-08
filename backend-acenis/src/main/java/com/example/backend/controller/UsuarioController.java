package com.example.backend.controller;

import com.example.backend.dto.UsuarioUpdateRequest;
import com.example.backend.model.TipoUsuario; // Importe o enum
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://loginacenisss.vercel.app")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmailUser(usuario.getEmailUser())) {
            throw new RuntimeException("Email já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest dadosAtualizacao) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuarioParaAtualizar = usuarioOpt.get();
        usuarioParaAtualizar.setJob(dadosAtualizacao.getJob());
        usuarioParaAtualizar.setRegister(dadosAtualizacao.getRegister());
        final Usuario usuarioAtualizado = usuarioRepository.save(usuarioParaAtualizar);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    /**
     * Rota para a página de Profissionais.
     * Retorna uma lista contendo apenas os usuários com tipo PROFISSIONAL.
     */
    @GetMapping("/profissionais")
    public List<Usuario> listarProfissionais() {
        return usuarioRepository.findByTipo(TipoUsuario.PROFISSIONAL);
    }

    /**
     * Rota para a página de Responsáveis.
     * Retorna uma lista contendo apenas os usuários com tipo RESPONSAVEL.
     */
    @GetMapping("/responsaveis")
    public List<Usuario> listarResponsaveis() {
        return usuarioRepository.findByTipo(TipoUsuario.RESPONSAVEL);
    }
}
