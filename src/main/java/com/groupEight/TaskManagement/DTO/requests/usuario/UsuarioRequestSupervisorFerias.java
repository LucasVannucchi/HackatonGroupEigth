package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UsuarioRequestSupervisorFerias(
        @NotNull String emailParaNovoSupervisor,
        @NotNull LocalDateTime dataInicio,
        @NotNull LocalDateTime dataFinal,
        @NotNull Permissoes permissoes
) {
}
