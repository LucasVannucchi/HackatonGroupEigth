package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.models.HistoricoTarefa;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HistoricoMapper {

    public HistoricoTarefa toHistorico(Tarefa tarefa, UsuarioModel usuarioAlteracao, String comentario) {
        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setTitulo(tarefa.getTitulo());
        historico.setDescricao(tarefa.getDescricao());
        historico.setUsuario(tarefa.getUsuario());
        historico.setStatus(tarefa.getStatus());
        historico.setAcao(tarefa.getAcao());
        historico.setPrioridade(tarefa.getPrioridade());
        historico.setDataCriacao(tarefa.getDataCriacao());
        historico.setDataInicio(tarefa.getDataInicio());
        historico.setDataPrevista(tarefa.getDataPrevista());
        historico.setDataFim(tarefa.getDataFim());
        historico.setUsuarioAlteracao(usuarioAlteracao);
        historico.setComentario(comentario);
        historico.setDataAtualizacao(LocalDateTime.now());
        return historico;
    }
}

