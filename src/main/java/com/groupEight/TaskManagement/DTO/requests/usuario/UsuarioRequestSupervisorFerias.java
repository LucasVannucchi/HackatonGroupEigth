package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;

import java.time.LocalDateTime;

public record UsuarioRequestSupervisorFerias(
        String emailGestor,
        String emailSubstituto,
        LocalDateTime dataInicio,
        LocalDateTime dataFinal
) {
}
