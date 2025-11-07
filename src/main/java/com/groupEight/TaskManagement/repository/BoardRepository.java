package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.models.Board;
import com.groupEight.TaskManagement.models.Equipe;
import com.groupEight.TaskManagement.models.Tarefa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Repository responsável pela persistência e consultas da entidade Board.
 * Inclui métodos de busca detalhados por nome, equipe e tarefas.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 🔹 Busca exata pelo nome da board
    Optional<Board> findByNome(String nome);

    // 🔹 Busca por nome contendo parte do texto (case insensitive)
    List<Board> findByNomeContainingIgnoreCase(String nome);

    // 🔹 Busca boards associadas a uma equipe específica
    List<Board> findByEquipe(Equipe equipe);

    // 🔹 Busca boards que contenham uma tarefa específica
    @Query("SELECT b FROM Board b JOIN b.tarefas t WHERE t = :tarefa")
    Optional<Board> findByTarefa(@Param("tarefa") Tarefa tarefa);

    // 🔹 Busca boards que tenham tarefas com um título específico
    @Query("SELECT b FROM Board b JOIN b.tarefas t WHERE LOWER(t.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Board> findByTarefaTitulo(@Param("titulo") String titulo);

    // 🔹 Busca boards com base no ID da equipe
    @Query("SELECT b FROM Board b WHERE b.equipe.id = :idEquipe")
    List<Board> findByEquipeId(@Param("idEquipe") Long idEquipe);

    // 🔹 Verifica se já existe uma board com o nome informado
    boolean existsByNome(String nome);

    // 🔹 Verifica se existe uma board associada a uma equipe específica
    boolean existsByEquipe(Equipe equipe);
}