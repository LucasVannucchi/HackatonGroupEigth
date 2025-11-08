package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.models.UsuarioModel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        UsuarioResponseDto responsavel,
        TipoPrioridade prioridade,
        TipoStatus status,
        LocalDateTime dataCriacao,
        LocalDateTime dataInicio,
        LocalDateTime dataPrevista,
        LocalDateTime dataFim
) {
}
