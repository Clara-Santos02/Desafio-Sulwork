package com.example.demo.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.StatusItem;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ItemCafeDTO(
        @NotBlank(message = "Nome do item é obrigatório")
        String nomeItem,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @Future(message = "A data deve ser maior que a de hoje")
        LocalDate data,

        StatusItem status,

        Integer colaboradorId
) {}