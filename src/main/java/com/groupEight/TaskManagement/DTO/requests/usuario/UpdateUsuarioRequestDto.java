package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateUsuarioRequestDto(
        @NotNull String nome,
        @NotNull String email,
        UsuarioStatus status,
        @NotNull String senha,
        @NotNull String cargo,
        @NotNull Permissoes permissoes
) {
}
