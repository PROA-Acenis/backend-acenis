package com.example.backend.controller;

import com.example.backend.dto.UsuarioRegistrationDTO;
import com.example.backend.dto.UsuarioUpdateRequest;
import com.example.backend.model.TipoUsuario;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.repository.FollowRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final FollowRepository followRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, FollowRepository followRepository) {
        this.usuarioRepository = usuarioRepository;
        this.followRepository = followRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioRegistrationDTO registrationDTO) {
        if (usuarioRepository.existsByEmailUser(registrationDTO.getEmailUser())) {
            return ResponseEntity.status(409).body(null);
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNameUser(registrationDTO.getNameUser());
        novoUsuario.setEmailUser(registrationDTO.getEmailUser());
        novoUsuario.setPasswordUser(registrationDTO.getPasswordUser());
        novoUsuario.setTipo(registrationDTO.getTipo());

        if (registrationDTO.getJob() != null) {
            novoUsuario.setJob(registrationDTO.getJob());
        }
        if (registrationDTO.getRegister() != null) {
            novoUsuario.setRegister(registrationDTO.getRegister());
        }
        if (registrationDTO.getCnpj() != null) {
            novoUsuario.setCnpj(registrationDTO.getCnpj());
        }
        if (registrationDTO.getCategoria() != null) {
            novoUsuario.setCategoria(registrationDTO.getCategoria());
        }

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(201).body(usuarioSalvo);
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
        usuarioParaAtualizar.setNameUser(dadosAtualizacao.getNameUser());
        usuarioParaAtualizar.setEmailUser(dadosAtualizacao.getEmailUser());
        usuarioParaAtualizar.setPasswordUser(dadosAtualizacao.getPasswordUser());
        usuarioParaAtualizar.setJob(dadosAtualizacao.getJob());
        usuarioParaAtualizar.setRegister(dadosAtualizacao.getRegister());

        final Usuario usuarioAtualizado = usuarioRepository.save(usuarioParaAtualizar);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profissionais")
    public List<Usuario> listarProfissionais() {
        return usuarioRepository.findByTipo(TipoUsuario.PROFISSIONAL);
    }

    @GetMapping("/responsaveis")
    public List<Usuario> listarResponsaveis() {
        return usuarioRepository.findByTipo(TipoUsuario.RESPONSAVEL);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        return usuarioOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/followStats")
    public ResponseEntity<Map<String, Long>> getFollowStats(@PathVariable Integer userId) {
        Optional<Usuario> userOpt = usuarioRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Usuario user = userOpt.get();

        Long followersCount = followRepository.countByFollowed_IdUser(user.getIdUser());
        Long followingCount = followRepository.countByFollower_IdUser(user.getIdUser());

        return ResponseEntity.ok(Map.of(
                "followersCount", followersCount,
                "followingCount", followingCount
        ));
    }
}