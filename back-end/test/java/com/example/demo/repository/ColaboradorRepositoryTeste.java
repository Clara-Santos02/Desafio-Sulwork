package com.example.demo.repository;

import com.example.demo.model.Colaborador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ColaboradorRepositoryTeste {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve salvar e recuperar um colaborador")
    void testSalvarERecuperarColaborador() {
        Colaborador colaborador = new Colaborador();
        colaborador.setNome("Clara Santos");
        colaborador.setCpf("08611015452");

        Colaborador salvo = colaboradorRepository.save(colaborador);

        Optional<Colaborador> encontrado = colaboradorRepository.findById(salvo.getId());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("Clara Santos");
        assertThat(encontrado.get().getCpf()).isEqualTo("08611015452");
    }
}
