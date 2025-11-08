package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.Acoes;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
public record HistoricoTarefaResponseDTO(
        LocalDateTime dataAtualizacao,
        Long idTarefa,
        Long id,
        String titulo,
        String descricao,
        String responsavel,
        String responsavelAlterador,
        TipoStatus status,
        Acoes acoes,
        TipoPrioridade prioridade,
        String acaoRealizada,
        LocalDateTime dataCriacao,
        LocalDateTime dataInicio,
        LocalDateTime dataPrevista,
        LocalDateTime dataFim
) {
}
