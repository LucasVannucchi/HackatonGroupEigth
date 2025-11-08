package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.models.Tarefa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TarefaMapper {

    public static Tarefa toTarefa(TarefaRequestDTO tarefaRequestDTO){
        return Tarefa.builder()
                .titulo(tarefaRequestDTO.titulo())
                .descricao(tarefaRequestDTO.descricao())
                .status(TipoStatus.Pendente)
                .prioridade(tarefaRequestDTO.prioridade())
                .dataCriacao(LocalDateTime.now())
                .dataInicio(tarefaRequestDTO.dataInicio())
                .dataPrevista(tarefaRequestDTO.dataPrevista())
                .build();
    }

    public static List<Tarefa> toListTarefa(List<TarefaRequestDTO> tarefaRequestDTO){
        if (tarefaRequestDTO == null){
            return List.of();
        }
        return tarefaRequestDTO.stream()
                .map(TarefaMapper::toTarefa)
                .collect(Collectors.toList());
    }

    public static TarefaResponseDTO toTarefaResponseDTO(Tarefa tarefa){
        UsuarioResponseDto usuarioResponseDto = null;

        if (tarefa.getUsuario() != null) {
            usuarioResponseDto = new UsuarioResponseDto(
                    tarefa.getUsuario().getId(),
                    tarefa.getUsuario().getNome(),
                    tarefa.getUsuario().getEmail(),
                    tarefa.getUsuario().getCargo(),
                    tarefa.getUsuario().getStatus(),
                    tarefa.getUsuario().getPermissoes()
            );
        }

        return TarefaResponseDTO.builder()
                .id(tarefa.getId())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .responsavel(usuarioResponseDto)
                .status(tarefa.getStatus())
                .prioridade(tarefa.getPrioridade())
                .dataCriacao(tarefa.getDataCriacao())
                .dataInicio(tarefa.getDataInicio())
                .dataPrevista(tarefa.getDataPrevista())
                .dataFim(tarefa.getDataFim())
                .build();
    }


    public static List<TarefaResponseDTO> toTarefaResponseDTO(List<Tarefa> tarefas){
        if (tarefas == null){
            return List.of();
        }
        return tarefas.stream()
                .map(TarefaMapper::toTarefaResponseDTO)
                .collect(Collectors.toList());
    }
}
