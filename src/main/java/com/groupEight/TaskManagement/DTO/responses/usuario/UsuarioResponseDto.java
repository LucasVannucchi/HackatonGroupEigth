package com.groupEight.TaskManagement.DTO.responses.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String cargo,
        UsuarioStatus status,
        Permissoes permissoes
) {
}
