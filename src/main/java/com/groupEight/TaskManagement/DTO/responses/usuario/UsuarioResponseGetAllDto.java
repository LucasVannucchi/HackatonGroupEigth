package com.groupEight.TaskManagement.DTO.responses.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;

import java.util.UUID;

public record UsuarioResponseGetAllDto(
        UUID id,
        String nome,
        String email,
        String senha,
        String cargo,
        UsuarioStatus status,
        Permissoes permissoes,
        String equipeNome
) {
}
