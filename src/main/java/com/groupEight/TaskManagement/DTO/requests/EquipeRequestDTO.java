package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enuns.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipeRequestDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotNull(message = "Setor é obrigatório") Setor setor
){


}
