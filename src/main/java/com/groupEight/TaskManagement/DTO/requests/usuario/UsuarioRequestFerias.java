package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.UsuarioStatus;

import java.time.LocalDate;

public record UsuarioRequestFerias(
        String email,
        UsuarioStatus status,
        LocalDate dataInicio,
        LocalDate dataFinal
) {

}
