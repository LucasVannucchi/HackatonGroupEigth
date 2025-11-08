package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.models.UsuarioModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefaRequestDTO(
        String titulo,
        String descricao,
        TipoPrioridade prioridade,
        TipoStatus status,
        LocalDateTime dataInicio,
        LocalDateTime dataPrevista
) {}
