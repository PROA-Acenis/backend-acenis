package com.example.backend.service;

import com.example.backend.dto.EventoDTO;
import com.example.backend.model.Cliente;
import com.example.backend.model.EventoAgenda;
import com.example.backend.model.Usuario;
import com.example.backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaService {

    private final EventoAgendaRepository eventoRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public AgendaService(EventoAgendaRepository er, ClienteRepository cr, UsuarioRepository ur) {
        this.eventoRepository = er;
        this.clienteRepository = cr;
        this.usuarioRepository = ur;
    }

    public List<EventoAgenda> listarPorProfissional(Integer profissionalId) {
        return eventoRepository.findByProfissionalIdUser(profissionalId);
    }

    public EventoAgenda criarEvento(EventoDTO dto) {
        System.out.println("DEBUG: Service - Criando evento para profissional ID: " + dto.getProfissionalId());
        Usuario profissional = usuarioRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        EventoAgenda novoEvento = new EventoAgenda();
        novoEvento.setProfissional(profissional);
        novoEvento.setCliente(cliente);
        novoEvento.setDataEvento(dto.getDataEvento());
        novoEvento.setHoraEvento(dto.getHoraEvento());
        novoEvento.setTipoEvento(dto.getTipoEvento());
        novoEvento.setCor(dto.getCor());

        return eventoRepository.save(novoEvento);
    }

    public void deletarEvento(Integer id) {
        eventoRepository.deleteById(id);
    }
}