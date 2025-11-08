package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
        @NotNull String nome,
        @NotNull String email,
        @NotNull String senha,
        @NotNull String cargo
) {
}
