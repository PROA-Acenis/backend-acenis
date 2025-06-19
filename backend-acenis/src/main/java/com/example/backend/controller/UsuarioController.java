package com.example.backend.controller;

import com.example.backend.dto.UsuarioUpdateRequest;
import com.example.backend.model.TipoUsuario;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (bom para desenvolvimento)
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência do repositório
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Endpoint para CADASTRAR um novo usuário.
     * Método: POST
     * URL: /usuarios
     */
    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmailUser(usuario.getEmailUser())) {
            throw new RuntimeException("Email já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Endpoint para LISTAR TODOS os usuários.
     * Método: GET
     * URL: /usuarios
     */
    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Endpoint para ATUALIZAR um usuário existente pelo ID.
     * Método: PUT
     * URL: /usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioUpdateRequest dadosAtualizacao) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Atualiza os campos do usuário com os dados recebidos
        Usuario usuarioParaAtualizar = usuarioOpt.get();
        usuarioParaAtualizar.setNameUser(dadosAtualizacao.getNameUser());
        usuarioParaAtualizar.setEmailUser(dadosAtualizacao.getEmailUser());
        usuarioParaAtualizar.setPasswordUser(dadosAtualizacao.getPasswordUser());
        usuarioParaAtualizar.setJob(dadosAtualizacao.getJob());
        usuarioParaAtualizar.setRegister(dadosAtualizacao.getRegister());

        // Salva o usuário atualizado no banco
        final Usuario usuarioAtualizado = usuarioRepository.save(usuarioParaAtualizar);

        // Retorna 200 OK com o usuário atualizado no corpo da resposta
        return ResponseEntity.ok(usuarioAtualizado);
    }

    /**
     * Endpoint para DELETAR um usuário pelo ID.
     * Método: DELETE
     * URL: /usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        // Verifica se o usuário com o ID fornecido existe no banco de dados
        if (!usuarioRepository.existsById(id)) {
            // Se não existir, retorna o status 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Se o usuário existir, deleta-o do banco
        usuarioRepository.deleteById(id);

        // Retorna o status 204 No Content, indicando que a operação foi bem-sucedida
        // e não há conteúdo para retornar no corpo da resposta.
        return ResponseEntity.noContent().build();
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

    /**
     * Endpoint para BUSCAR um usuário específico pelo ID.
     * Método: GET
     * URL: /usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        // Tenta encontrar o usuário no repositório pelo ID fornecido
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        // Se o Optional não estiver vazio (usuário encontrado), retorna 200 OK com o usuário
        // Se estiver vazio (usuário não encontrado), retorna 404 Not Found
        return usuarioOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
