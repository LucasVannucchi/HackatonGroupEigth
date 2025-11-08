package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioRequestFerias(
        @NotNull String email,
        @NotNull UsuarioStatus status,
        @NotNull LocalDateTime dataInicio,
        @NotNull LocalDateTime dataFinal
) {

}
