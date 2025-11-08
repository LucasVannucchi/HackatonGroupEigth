package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.enuns.Acoes;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record HistoricoTarefaResponseDTO(
        long id,
        long tarefaId,
        long usuarioId,
        String comentario,
        Acoes acao,
        LocalDateTime dataAtualizacao
) {
}
