package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UsuarioRequestDesligamento(
        @NotNull String email,
        @NotNull UsuarioStatus status,
        @NotNull LocalDateTime dataDesligamento
) {

}
