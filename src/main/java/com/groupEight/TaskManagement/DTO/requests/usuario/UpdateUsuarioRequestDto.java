package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateUsuarioRequestDto(
        @NotNull String nome,
        @NotNull String email,
        @NotNull String senha,
        @NotNull String cargo,
        @NotNull Permissoes permissoes
) {
}
