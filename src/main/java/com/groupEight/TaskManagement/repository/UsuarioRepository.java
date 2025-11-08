package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
    Optional<UsuarioModel> findByNome(String nome);

}
