package com.groupEight.TaskManagement.DTO.responses.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;

import java.time.LocalDateTime;

public record UsuarioResponseGestorFeriasDto(
         String NovoSupervisor,
         Permissoes permissoesSupervisor,
         String GestorDeFerias
) {
}
