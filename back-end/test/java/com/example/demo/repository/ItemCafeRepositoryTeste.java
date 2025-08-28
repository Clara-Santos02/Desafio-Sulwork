package com.example.demo.repository;

import com.example.demo.model.Colaborador;
import com.example.demo.model.ItemCafe;
import com.example.demo.model.StatusItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemCafeRepositoryTeste {

    @Autowired
    private ItemCafeRepository itemCafeRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Deve salvar e recuperar um item de café")
    void testSalvarERecuperarItemCafe() {
        
        Colaborador colaborador = new Colaborador();
        colaborador.setNome("Clara Santos");
        colaborador.setCpf("08611015452");
        Colaborador salvoColaborador = colaboradorRepository.save(colaborador);

        ItemCafe item = new ItemCafe();
        item.setNomeItem("Pão");
        item.setData(LocalDate.now().plusDays(1));
        item.setStatus(StatusItem.PENDENTE);
        item.setColaborador(salvoColaborador);

        ItemCafe salvoItem = itemCafeRepository.save(item);

        Optional<ItemCafe> encontrado = itemCafeRepository.findById(salvoItem.getId());
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNomeItem()).isEqualTo("Pão");
        assertThat(encontrado.get().getColaborador().getNome()).isEqualTo("Clara Santos");
    }
}