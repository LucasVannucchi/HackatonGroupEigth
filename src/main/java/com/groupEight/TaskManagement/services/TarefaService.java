package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.DTO.responses.UsuarioResponseDto;
import com.groupEight.TaskManagement.mappers.TarefaMapper;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.HistoricoRepository;
import com.groupEight.TaskManagement.repository.TarefasRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private TarefasRepository tarefasRepository;
    private HistoricoRepository historicoRepository;

    public TarefaService(TarefasRepository tarefasRepository, HistoricoRepository historicoRepository){
        this.tarefasRepository = tarefasRepository;
        this.historicoRepository = historicoRepository;
    }

    public TarefaResponseDTO buscarById(Long Id) {
        Tarefa tarefa = tarefasRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tarefa not found"));
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public TarefaResponseDTO criarTarefa(TarefaRequestDTO tarefaRequestDTO){
        Tarefa tarefa = TarefaMapper.toTarefa(tarefaRequestDTO);

        Tarefa salvarTarefa = tarefasRepository.save(tarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);

    }
}
