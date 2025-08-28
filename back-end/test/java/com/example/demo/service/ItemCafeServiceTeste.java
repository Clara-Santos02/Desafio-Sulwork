package com.example.demo.service;

import com.example.demo.dto.ItemCafeDTO;
import com.example.demo.model.StatusItem;
import com.example.demo.model.ItemCafe;
import com.example.demo.repository.ColaboradorRepository;
import com.example.demo.repository.ItemCafeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemCafeServiceTeste {

    @Mock
    private ItemCafeRepository itemCafeRepository;

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    private ItemCafeService itemCafeService;

    @Test
    void testarCadastroItemSucesso() {
        ItemCafeDTO dto = new ItemCafeDTO("Pão", LocalDate.now().plusDays(1), StatusItem.PENDENTE, 1);

        when(colaboradorRepository.existsById(1)).thenReturn(true);
        when(itemCafeRepository.procurarPorNomeEData(dto.nomeItem(), dto.data())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> itemCafeService.cadastrarItem(dto));

        verify(itemCafeRepository, times(1))
                .inserirItem(dto.nomeItem(), dto.data(), StatusItem.PENDENTE.name(), dto.colaboradorId());
    }

   @Test
    void testarCadastroItemDuplicado() {
        LocalDate dataFutura = LocalDate.now().plusDays(1);
        ItemCafeDTO dto = new ItemCafeDTO("Pão", dataFutura, StatusItem.PENDENTE, 1);

        when(colaboradorRepository.existsById(1)).thenReturn(true);
        when(itemCafeRepository.procurarPorNomeEData(dto.nomeItem(), dto.data()))
                .thenReturn(Optional.of(new ItemCafe()));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> itemCafeService.cadastrarItem(dto));

        assertEquals("Este item já foi escolhido para a data " + dto.data(), exception.getMessage());
    }

    @Test
    void testarAtualizarStatusExistente() {
        when(itemCafeRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> itemCafeService.atualizarStatus(1, StatusItem.ENTREGUE));

        verify(itemCafeRepository, times(1)).atualizarStatus(1, StatusItem.ENTREGUE.name());
    }

    @Test
    void testarAtualizarStatusInexistente() {
        when(itemCafeRepository.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> itemCafeService.atualizarStatus(1, StatusItem.ENTREGUE));

        assertEquals("ID inexistente", exception.getMessage());
    }

    @Test
    void testarDeletarItemExistente() {
        when(itemCafeRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> itemCafeService.deletarItem(1));

        verify(itemCafeRepository, times(1)).deletarItem(1);
    }

    @Test
    void testarDeletarItemInexistente() {
        when(itemCafeRepository.existsById(1)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> itemCafeService.deletarItem(1));

        assertEquals("ID inexistente", exception.getMessage());
    }
}