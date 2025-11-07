package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Usuario proprietario,
        TipoPrioridade prioridade,
        TipoStatus status,
        LocalDateTime dataInicio,
        LocalDateTime dataPrevista,
        LocalDateTime dataFim
) {
}
