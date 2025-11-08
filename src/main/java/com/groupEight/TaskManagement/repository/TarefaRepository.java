package com.groupEight.TaskManagement.repository;

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

    @Query("""
        SELECT t FROM Tarefa t
        WHERE t.usuario.id = :usuarioId
          AND (
                (t.dataInicio BETWEEN :inicio AND :fim)
                OR (t.dataFim BETWEEN :inicio AND :fim)
                OR (t.dataInicio <= :inicio AND t.dataFim >= :fim)
              )
    """)
    List<Tarefa> findTarefasDuranteFerias(
            @Param("usuarioId") UUID usuarioId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
