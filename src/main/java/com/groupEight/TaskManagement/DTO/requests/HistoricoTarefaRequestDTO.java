package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enuns.Acoes;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HistoricoTarefaRequestDTO(
        List<TarefaRequestDTO> tarefas,
        LocalDateTime dataAtualizacao
) {
}
