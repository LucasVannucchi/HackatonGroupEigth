package com.groupEight.TaskManagement.DTO.requests.usuario;

import com.groupEight.TaskManagement.enuns.Permissoes;

public record UsuarioRequestDto(
        String nome,
        String email,
        String senha,
        String cargo,
        Permissoes permissoes
) {
}
