package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;

import java.util.UUID;

public record UpdateUsuarioRequestDto(
        String nome,
        String email,
        String senha,
        String cargo,
        Permissoes permissoes
) {
}
