package com.groupEight.TaskManagement.DTO.responses;

import java.util.List;

/**
 * DTO de saída (resposta) para retorno de dados da Board.
 */
public record BoardResponseDTO(

        Long id,
        String nome,

        /**
         * Campo referente à entidade Equipe.
         * ⚠️ Será atualizado futuramente para retornar um objeto EquipeResponse.
         */
        Long equipeId,

        /**
         * Lista de IDs de tarefas associadas.
         * ⚠️ Será atualizada futuramente para retornar List<TarefaResponse>.
         */
        List<Long> tarefasIds
) {}