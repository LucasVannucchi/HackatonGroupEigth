package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.enums.UsuarioStatus;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String cargo,
        UsuarioStatus status
) {
}
