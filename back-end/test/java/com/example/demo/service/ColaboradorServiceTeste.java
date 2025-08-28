package com.example.demo.service;

import com.example.demo.dto.ColaboradorDTO;
import com.example.demo.model.Colaborador;
import com.example.demo.repository.ColaboradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class ColaboradorServiceTeste {


    @InjectMocks
    private ColaboradorService colaboradorService;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testarCadastroCpfExistente() {
        ColaboradorDTO dto = new ColaboradorDTO("Clara Santos", "08611015452");
        Colaborador existente = new Colaborador();
        existente.setNome("Clara Santos");
        existente.setCpf("08611015452");

        when(colaboradorRepository.procurarPorCpf("08611015452")).thenReturn(Optional.of(existente));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
        colaboradorService.cadastrarColaborador(dto);
            });


        assertEquals("CPF já cadastrado!", exception.getMessage());
    }
}