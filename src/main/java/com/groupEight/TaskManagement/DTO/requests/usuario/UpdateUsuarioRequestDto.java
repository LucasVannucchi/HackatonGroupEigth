package com.groupEight.TaskManagement.DTO.requests.usuario;

import java.util.UUID;

public record UpdateUsuarioRequestDto(
        UUID id,
        String nome,
        String senha,
        String cargo
) {
}
