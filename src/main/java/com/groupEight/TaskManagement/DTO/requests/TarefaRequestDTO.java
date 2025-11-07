package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefaRequestDTO(
         String titulo,
         String descricao,
         Usuario proprietario,
         TipoPrioridade prioridade,
         TipoStatus status,
         LocalDateTime dataInicio,
         LocalDateTime dataPrevista,
         LocalDateTime dataFim
){ }
