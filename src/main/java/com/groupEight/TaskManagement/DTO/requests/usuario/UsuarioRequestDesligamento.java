package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;

import java.time.LocalDateTime;

public record UsuarioRequestDesligamento(
        String email,
        UsuarioStatus status,
        LocalDateTime dataDesligamento
) {

}
