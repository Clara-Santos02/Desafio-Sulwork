package com.example.demo.controller;

import com.example.demo.dto.ItemCafeDTO;
import com.example.demo.model.StatusItem;
import com.example.demo.service.ItemCafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class ItemCafeController {

    private final ItemCafeService itemCafeService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody ItemCafeDTO dto) {
        try {
            itemCafeService.cadastrarItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Item de café cadastrado!");
        } catch (RuntimeException e) {
            if ("ID inexistente".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            var itens = itemCafeService.listarTodosItens();
            if (itens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum item de café encontrado");
            }
            return ResponseEntity.ok(itens);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

    @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatus(@PathVariable Integer id, @RequestParam StatusItem status) {
        try {
            itemCafeService.atualizarStatus(id, status);
            return ResponseEntity.ok("Status do item atualizado!");
        } catch (RuntimeException e) {
           if ("ID inexistente".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        try {
            itemCafeService.deletarItem(id);
            return ResponseEntity.ok("Item de café deletado!");
        } catch (RuntimeException e) {
            if ("ID inexistente".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}