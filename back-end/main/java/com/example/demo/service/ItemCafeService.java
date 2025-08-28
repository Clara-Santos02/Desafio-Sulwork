package com.example.demo.service;

import com.example.demo.dto.ItemCafeDTO;
import com.example.demo.model.ItemCafe;
import com.example.demo.model.StatusItem;
import com.example.demo.repository.ColaboradorRepository;
import com.example.demo.repository.ItemCafeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCafeService {

    @Autowired
    private final ItemCafeRepository itemCafeRepository;

    @Autowired
    private final ColaboradorRepository colaboradorRepository;

    @Transactional
    public void cadastrarItem(ItemCafeDTO dto) {
        if (!colaboradorRepository.existsById(dto.colaboradorId())) {
        throw new RuntimeException("Colaborador não existe!");
        }
        if (itemCafeRepository.procurarPorNomeEData(dto.nomeItem(), dto.data()).isPresent()) {
            throw new RuntimeException("Este item já foi escolhido para a data " + dto.data());
        }

        itemCafeRepository.inserirItem(dto.nomeItem(), dto.data(), StatusItem.PENDENTE.name(), dto.colaboradorId());
    }

    @Transactional
    public void atualizarStatus(Integer id, StatusItem novoStatus) {
        if (!itemCafeRepository.existsById(id)) {
        throw new RuntimeException("ID inexistente");
        }   
        itemCafeRepository.atualizarStatus(id, novoStatus.name());
    }

    @Transactional
    public void deletarItem(Integer id) {
        if (!itemCafeRepository.existsById(id)) {
        throw new RuntimeException("ID inexistente");
        }
        itemCafeRepository.deletarItem(id);
    }

    @Transactional
    public void verificarItensAtrasados() {
        var hoje = LocalDate.now();
        var todos = itemCafeRepository.findAll();

        todos.stream()
                .filter(item -> item.getData().isBefore(hoje) && item.getStatus() == StatusItem.PENDENTE)
                .forEach(item -> itemCafeRepository.atualizarStatus(item.getId(), StatusItem.NAO_ENTREGUE.name()));
    }

    @Transactional
    public List<ItemCafe> listarTodosItens() {
    return itemCafeRepository.findAll();
}
}