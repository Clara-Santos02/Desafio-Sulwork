package com.example.demo.service;

import com.example.demo.dto.ColaboradorDTO;
import com.example.demo.model.Colaborador;
import com.example.demo.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Transactional
    public void cadastrarColaborador(ColaboradorDTO dto) {
        if (colaboradorRepository.procurarPorCpf(dto.cpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado!");
        }

        if (colaboradorRepository.procurarPorNome(dto.nome()).isPresent()) {
            throw new RuntimeException("Colaborador já cadastradado.");
        }

        colaboradorRepository.inserirColaborador(dto.nome(), dto.cpf());
    }

    @Transactional
    public void atualizarColaborador(Integer id, ColaboradorDTO dto) {
        colaboradorRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("ID inexistente!"));

        colaboradorRepository.procurarPorCpf(dto.cpf())
                .filter(c -> !c.getId().equals(id)) 
                .ifPresent(c -> {
                    throw new RuntimeException("CPF já cadastrado!");
                });

        colaboradorRepository.procurarPorNome(dto.nome())
                .filter(c -> !c.getId().equals(id))
                .ifPresent(c -> {
                    throw new RuntimeException("Colaborador já cadastradado.");
                });

        colaboradorRepository.atualizarColaborador(id, dto.nome(), dto.cpf());
    }

    @Transactional
    public void deletarColaborador(Integer id) {
        if (!colaboradorRepository.existsById(id)) {
        throw new RuntimeException("ID inexistente");
        }
        colaboradorRepository.deleteById(id);
    }

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> findById(Integer id) {
        return colaboradorRepository.findById(id);
    }
}