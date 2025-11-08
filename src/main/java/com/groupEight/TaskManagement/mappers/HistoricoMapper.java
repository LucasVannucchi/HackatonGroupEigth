package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.responses.HistoricoTarefaResponseDTO;
import com.groupEight.TaskManagement.models.HistoricoTarefa;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoricoMapper {

    public static HistoricoTarefa toHistorico(Tarefa tarefa, UsuarioModel usuarioAlteracao, String acaoRealizada) {
        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);

        // ✅ Copiar o usuário da tarefa (caso exista)
        if (tarefa.getUsuario() != null) {
            historico.setUsuario(tarefa.getUsuario());
        }

        // 🔹 Vincula a tarefa (essencial)
        historico.setTarefa(tarefa);

        // 🔹 Copia os dados da tarefa
        historico.setTitulo(tarefa.getTitulo());
        historico.setDescricao(tarefa.getDescricao());
        historico.setStatus(tarefa.getStatus());
        historico.setAcao(tarefa.getAcao());
        historico.setPrioridade(tarefa.getPrioridade());
        historico.setDataCriacao(tarefa.getDataCriacao());
        historico.setDataInicio(tarefa.getDataInicio());
        historico.setDataPrevista(tarefa.getDataPrevista());
        historico.setDataFim(tarefa.getDataFim());

        // 🔹 Quem fez a alteração
        historico.setUsuarioAlteracao(usuarioAlteracao);

        // 🔹 Informação da ação
        historico.setAcaoRealizada(acaoRealizada);
        historico.setDataAtualizacao(LocalDateTime.now());

        return historico;
    }

    public static HistoricoTarefaResponseDTO toHistoricoResponseDTO(HistoricoTarefa historico) {
        var usuario = StringUtils.EMPTY;

        if (historico.getUsuario() != null) {
            usuario = historico.getUsuario().getNome();
        }

        // ⚙️ Proteção contra NPE se a tarefa for nula
        Long idTarefa = historico.getTarefa() != null ? historico.getTarefa().getId() : null;
        String responsavelAlterador = historico.getUsuarioAlteracao() != null ? historico.getUsuarioAlteracao().getNome() : null;

        return HistoricoTarefaResponseDTO.builder()
                .dataAtualizacao(historico.getDataAtualizacao())
                .idTarefa(idTarefa)
                .id(historico.getId())
                .titulo(historico.getTitulo())
                .descricao(historico.getDescricao())
                .responsavel(usuario)
                .responsavelAlterador(responsavelAlterador)
                .status(historico.getStatus())
                .acoes(historico.getAcao())
                .prioridade(historico.getPrioridade())
                .acaoRealizada(historico.getAcaoRealizada())
                .dataCriacao(historico.getDataCriacao())
                .dataInicio(historico.getDataInicio())
                .dataPrevista(historico.getDataPrevista())
                .dataFim(historico.getDataFim())
                .build();
    }

    public static List<HistoricoTarefaResponseDTO> toListHistoricoResponseDTO(List<HistoricoTarefa> historicos) {
        return historicos.stream()
                .map(HistoricoMapper::toHistoricoResponseDTO)
                .collect(Collectors.toList());
    }
}
