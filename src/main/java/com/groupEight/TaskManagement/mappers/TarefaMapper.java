package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.models.Tarefa;

import java.util.List;
import java.util.stream.Collectors;

public class TarefaMapper {

    public static Tarefa toTarefa(TarefaRequestDTO tarefaRequestDTO){
        return Tarefa.builder()
                .titulo(tarefaRequestDTO.titulo())
                .descricao(tarefaRequestDTO.descricao())
                .usuario(tarefaRequestDTO.proprietario())
                .status(tarefaRequestDTO.status())
                .prioridade(tarefaRequestDTO.prioridade())
                .dataInicio(tarefaRequestDTO.dataInicio())
                .dataPrevista(tarefaRequestDTO.dataPrevista())
                .dataFim(tarefaRequestDTO.dataFim())
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
        return TarefaResponseDTO.builder()
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .proprietario(tarefa.getUsuario())
                .status(tarefa.getStatus())
                .prioridade(tarefa.getPrioridade())
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
