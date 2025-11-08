package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(UsuarioModel usuario);
    List<Tarefa> findByStatus(TipoStatus status);
    List<Tarefa> findByStatusAndUsuario(UsuarioModel usuario, TipoStatus status);
    List<Tarefa> findByPrioridade(TipoPrioridade prioridade);
    List<Tarefa> findByPrioridadeAndUsuario(UsuarioModel usuario, TipoPrioridade prioridade);
}
