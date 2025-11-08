package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enums.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipeRequestDTO(
        @NotBlank String nome,
        @NotNull Setor setor
){


}
