package com.groupEight.TaskManagement.DTO.requests;

import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public record UsuarioRequest(
        String nome,
        String email,
        String senha,
        String cargo,
        Permissoes permissoes,
        UsuarioStatus status
) {
}
