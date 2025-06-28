package com.example.backend.service;

import com.example.backend.dto.AnotacaoDTO;
import com.example.backend.model.Anotacao;
import com.example.backend.model.Cliente;
import com.example.backend.model.Usuario;
import com.example.backend.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public AnotacaoService(AnotacaoRepository ar, ClienteRepository cr, UsuarioRepository ur) {
        this.anotacaoRepository = ar;
        this.clienteRepository = cr;
        this.usuarioRepository = ur;
    }

    public Anotacao criarAnotacao(AnotacaoDTO dto) {
        System.out.println("DEBUG: Service - Criando anotação para profissional ID: " + dto.getProfissionalId());
        Usuario profissional = usuarioRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Anotacao novaAnotacao = new Anotacao();
        novaAnotacao.setTitulo(dto.getTitulo());
        novaAnotacao.setTexto(dto.getTexto());
        novaAnotacao.setProfissional(profissional);
        novaAnotacao.setCliente(cliente);
        novaAnotacao.setDataCriacao(LocalDateTime.now());

        return anotacaoRepository.save(novaAnotacao);
    }

    public List<Anotacao> listarPorProfissional(Integer profissionalId) {
        return anotacaoRepository.findByProfissionalIdUserOrderByDataCriacaoDesc(profissionalId);
    }

    public void deletarAnotacao(Integer id) {
        anotacaoRepository.deleteById(id);
    }
}