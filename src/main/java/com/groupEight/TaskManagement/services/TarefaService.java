package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.AtribuirTarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.TarefaResponseDTO;
import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.mappers.HistoricoMapper;
import com.groupEight.TaskManagement.mappers.TarefaMapper;
import com.groupEight.TaskManagement.models.HistoricoTarefa;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.HistoricoRepository;
import com.groupEight.TaskManagement.repository.TarefaRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {
    private TarefaRepository tarefaRepository;
    private HistoricoRepository historicoRepository;
    private UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, HistoricoRepository historicoRepository, UsuarioRepository usuarioRepository){
        this.tarefaRepository = tarefaRepository;
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TarefaResponseDTO buscarById(Long Id) {
        Tarefa tarefa = tarefaRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public List<TarefaResponseDTO> buscarTodasTarefas(String email) {
        UsuarioModel usuario = (UsuarioModel) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));

        List<Tarefa> tarefas = tarefaRepository.findByUsuario(usuario);
        return TarefaMapper.toTarefaResponseDTO(tarefas);
    }


    public List<TarefaResponseDTO> buscarTodasTarefasUsuario(UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        List<Tarefa> tarefa = tarefaRepository.findByUsuario(usuario);
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public List<TarefaResponseDTO> buscarPorStatus(TipoStatus status){
        List<Tarefa> tarefa = tarefaRepository.findByStatus(status);
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public List<TarefaResponseDTO> buscarPorStatusFuncionario(UserDetails userDetails, TipoStatus status){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        List<Tarefa> tarefa = tarefaRepository.findByStatusAndUsuario(usuario,status);
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public List<TarefaResponseDTO> buscarPorPrioridade(TipoPrioridade prioridade){
        List<Tarefa> tarefa = tarefaRepository.findByPrioridade(prioridade);
        return TarefaMapper.toTarefaResponseDTO(tarefa);
    }

    public List<TarefaResponseDTO> buscarPorPrioridadeFuncionario(UserDetails userDetails, TipoPrioridade prioridade){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        List<Tarefa> tarefa = tarefaRepository.findByPrioridadeAndUsuario(usuario,prioridade);
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

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);

    }

    public TarefaResponseDTO atualizarTarefa(Long id, TarefaRequestDTO tarefaRequestDTO, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        tarefa.setTitulo(tarefaRequestDTO.titulo());
        tarefa.setDescricao(tarefaRequestDTO.descricao());
        tarefa.setStatus(tarefaRequestDTO.status());
        tarefa.setPrioridade(tarefaRequestDTO.prioridade());
        tarefa.setDataPrevista(tarefaRequestDTO.dataPrevista());
        tarefa.setUsuario(usuario);


        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa atualizada");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO atribuirTarefa(AtribuirTarefaRequestDTO atribuirTarefaRequestDTO, UserDetails userDetails){
        UsuarioModel lider = getUsuarioModel(userDetails);
        UsuarioModel usuario = (UsuarioModel) usuarioRepository.findByEmail(atribuirTarefaRequestDTO.emailUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Tarefa tarefa = tarefaRepository.findById(atribuirTarefaRequestDTO.idTarefa())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        tarefa.setUsuario(usuario);

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, lider, "Tarefa atribuida");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO iniciarTarefa(Long id, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        if (tarefa.getStatus() == TipoStatus.Cancelado){
            throw new RuntimeException("Tarefa cancelada, sem permissão!");
        }

        tarefa.setStatus(TipoStatus.Em_Andamento);
        tarefa.setDataInicio(LocalDateTime.now());

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa iniciada");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO analiseTarefa(Long id, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        if (tarefa.getStatus() == TipoStatus.Cancelado){
            throw new RuntimeException("Tarefa cancelada, sem permissão!");
        } else if (usuario.getPermissoes() == Permissoes.Gestor || usuario.getPermissoes() == Permissoes.Supervisor){

            tarefa.setStatus(TipoStatus.Em_Analise);

            HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa Em Analise");

            Tarefa salvarTarefa = tarefaRepository.save(tarefa);
            historicoRepository.save(historicoTarefa);
            return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
        } else if (tarefa.getUsuario() != usuario) {
            throw new RuntimeException("Usuario nao tem permissão para alterar status da tarefa!");
        }

        tarefa.setStatus(TipoStatus.Em_Analise);

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa Em Analise");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO concluirTarefa(Long id, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        if (tarefa.getStatus() == TipoStatus.Cancelado){
            throw new RuntimeException("Tarefa cancelada, sem permissão!");
        } else if  (usuario.getPermissoes() == Permissoes.Gestor || usuario.getPermissoes() == Permissoes.Supervisor){

            tarefa.setStatus(TipoStatus.Concluido);
            tarefa.setDataFim(LocalDateTime.now());

            HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa Concluida");

            Tarefa salvarTarefa = tarefaRepository.save(tarefa);
            historicoRepository.save(historicoTarefa);
            return TarefaMapper.toTarefaResponseDTO(salvarTarefa);

        } else if (tarefa.getUsuario() != usuario) {
            throw new RuntimeException("Usuario nao tem permissão para alterar status da tarefa!");
        } else if (tarefa.getStatus() != TipoStatus.Em_Analise){
            throw new RuntimeException("Tarefa não pode ser concluida!");
        }

        tarefa.setStatus(TipoStatus.Concluido);
        tarefa.setDataFim(LocalDateTime.now());

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa concluida");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO reabrirTarefa(Long id, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        tarefa.setStatus(TipoStatus.Pendente);
        tarefa.setDataFim(null);

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa reaberta");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }

    public TarefaResponseDTO cancelarTarefa(Long id, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));
        tarefa.setStatus(TipoStatus.Cancelado);
        tarefa.setDataFim(LocalDateTime.now());
        tarefa.setUsuario(usuario);

        HistoricoTarefa historicoTarefa = HistoricoMapper.toHistorico(tarefa, usuario, "Tarefa cancelada");

        Tarefa salvarTarefa = tarefaRepository.save(tarefa);
        historicoRepository.save(historicoTarefa);
        return TarefaMapper.toTarefaResponseDTO(salvarTarefa);
    }
}
