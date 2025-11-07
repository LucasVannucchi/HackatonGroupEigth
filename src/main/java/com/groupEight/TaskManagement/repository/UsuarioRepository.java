package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    UserDetails findByEmail(String email);
}
