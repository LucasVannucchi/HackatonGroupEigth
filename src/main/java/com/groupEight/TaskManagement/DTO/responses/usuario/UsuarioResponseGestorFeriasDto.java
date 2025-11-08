package com.groupEight.TaskManagement.DTO.responses.usuario;

import java.time.LocalDateTime;

public record UsuarioResponseGestorFeriasDto(
        UsuarioResponseDto gestor,
        UsuarioResponseDto substituto,
        LocalDateTime dataInicio,
        LocalDateTime dataFinal
) {
}
