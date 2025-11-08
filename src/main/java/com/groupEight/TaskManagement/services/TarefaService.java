package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.mappers.TarefaMapper;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.HistoricoRepository;
import com.groupEight.TaskManagement.repository.TarefaRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private TarefaRepository tarefaRepository;
    private HistoricoRepository historicoRepository;

    public TarefaService(TarefaRepository TarefaRepository, HistoricoRepository historicoRepository){
        this.tarefaRepository = TarefaRepository;
        this.historicoRepository = historicoRepository;
    }

    public TarefaResponseDTO buscarById(Long Id) {
        Tarefa tarefa = tarefaRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tarefa not found"));
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public TarefaResponseDTO criarTarefa(TarefaRequestDTO tarefaRequestDTO){
        Tarefa tarefa = TarefaMapper.toTarefa(tarefaRequestDTO);

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);

    }
}
