package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(UsuarioModel usuario);
    List<Tarefa> findByStatus(TipoStatus status);
    List<Tarefa> findByStatusAndUsuario(TipoStatus status, UsuarioModel usuario);
    List<Tarefa> findByPrioridade(TipoPrioridade prioridade);
    List<Tarefa> findByPrioridadeAndUsuario(TipoPrioridade prioridade, UsuarioModel usuario);

    @Query("""
    SELECT t
    FROM Tarefa t
    WHERE t.usuario.id = :usuarioId
      AND t.dataPrevista BETWEEN :dataInicio AND :dataFim
      AND t.status IN (:statusList)
""")
    List<Tarefa> findTarefasByUsuarioAndDataPrevistaBetweenAndStatusIn(
            @Param("usuarioId") UUID usuarioId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("statusList") List<TipoStatus> statusList
    );


    @Query("""
    SELECT t
    FROM Tarefa t
    WHERE t.usuario.id = :usuarioId
      AND t.dataPrevista > :dataDesligamento
      AND t.status IN (:statusList)
""")
    List<Tarefa> findTarefasAposDesligamentoComStatus(
            @Param("usuarioId") UUID usuarioId,
            @Param("dataDesligamento") LocalDateTime dataDesligamento,
            @Param("statusList") List<TipoStatus> statusList
    );
}
