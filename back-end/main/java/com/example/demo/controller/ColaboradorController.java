package com.example.demo.controller;

import com.example.demo.dto.ColaboradorDTO;
import com.example.demo.model.Colaborador;
import com.example.demo.service.ColaboradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody ColaboradorDTO dto) {
        try {
            colaboradorService.cadastrarColaborador(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Colaborador cadastrado!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> getAllColaboradores() {
        List<Colaborador> colaboradores = colaboradorService.findAll();
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getColaboradorById(@PathVariable Integer id) {
        return colaboradorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Integer id, @Valid @RequestBody ColaboradorDTO dto) {
        try {
            colaboradorService.atualizarColaborador(id, dto);
            return ResponseEntity.ok("Colaborador atualizado!");
        } catch (NoSuchElementException e) { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID inexistente");
            } catch (RuntimeException e) { 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
                }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        try {
            colaboradorService.deletarColaborador(id);
            return ResponseEntity.ok("Colaborador deletado!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inexistente");
            }
    }
}