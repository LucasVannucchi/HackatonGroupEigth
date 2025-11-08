package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
    Optional<UserDetails> findByEmail(String email);
    Optional<UsuarioModel> findByNome(String nome);
    boolean existsByEmail(String email);
}
