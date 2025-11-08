package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.mappers.HistoricoMapper;
import com.groupEight.TaskManagement.mappers.TarefaMapper;
import com.groupEight.TaskManagement.models.HistoricoTarefa;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.HistoricoRepository;
import com.groupEight.TaskManagement.repository.TarefasRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private TarefasRepository tarefasRepository;
    private HistoricoRepository historicoRepository;
    private UsuarioRepository usuarioRepository;

    public TarefaService(TarefasRepository tarefasRepository, HistoricoRepository historicoRepository, UsuarioRepository usuarioRepository){
        this.tarefasRepository = tarefasRepository;
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TarefaResponseDTO buscarById(Long Id) {
        Tarefa tarefa = tarefasRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tarefa not found"));
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    private UsuarioModel getUsuarioModel(UserDetails userDetails){
        return (UsuarioModel) usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }
    public TarefaResponseDTO criarTarefa(TarefaRequestDTO tarefaRequestDTO, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);

        Tarefa tarefa = TarefaMapper.toTarefa(tarefaRequestDTO);

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa criada");

        Tarefa salvarTarefa = tarefasRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);



    }
}
