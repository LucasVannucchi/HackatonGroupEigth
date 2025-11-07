package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.BoardRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.BoardResponseDTO;
import com.groupEight.TaskManagement.models.Board;
import com.groupEight.TaskManagement.models.Equipe;
import com.groupEight.TaskManagement.models.Tarefa;

import java.util.List;
import java.util.stream.Collectors;

public class BoardMapper {

    /**
     * Converte BoardRequest → Board (para salvar no banco)
     */
    public static Board toEntity(BoardRequestDTO request) {
        if (request == null) {
            return null;
        }

        Board board = new Board();
        board.setNome(request.nome());

        // ⚠️ Aqui estamos apenas instanciando a Equipe com o ID.
        // Posteriormente, o service deverá buscar a equipe completa no banco.
        if (request.equipeId() != null) {
            Equipe equipe = new Equipe();
            equipe.setId(request.equipeId());
            board.setEquipe(equipe);
        }

        // ⚠️ Tarefas ainda não são criadas diretamente no BoardRequest.
        // Esse campo pode ser gerenciado posteriormente.
        board.setTarefas(List.of());

        return board;
    }

    /**
     * Converte Board → BoardResponse (para retornar na API)
     */
    public static BoardResponseDTO toResponse(Board entity) {
        if (entity == null) {
            return null;
        }

        Long equipeId = entity.getEquipe() != null ? entity.getEquipe().getId() : null;

        List<Long> tarefasIds = entity.getTarefas() != null
                ? entity.getTarefas().stream()
                .map(Tarefa::getId)
                .collect(Collectors.toList())
                : List.of();

        return new BoardResponseDTO(
                entity.getId(),
                entity.getNome(),
                equipeId,
                tarefasIds
        );
    }

    /**
     * Converte lista de Boards → lista de BoardResponses
     */
    public static List<BoardResponseDTO> toResponseList(List<Board> boards) {
        return boards.stream()
                .map(BoardMapper::toResponse)
                .collect(Collectors.toList());
    }
}