package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String cargo,
        UsuarioStatus status
) {
}
