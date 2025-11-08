package com.groupEight.TaskManagement.DTO.responses.usuario;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;

public record UsuarioResponseFeriasDto(
        String nome,
        String email,
        UsuarioStatus status
) {
}
