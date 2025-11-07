package com.groupEight.TaskManagement.DTO.requests;

public record UsuarioRequestDto(
        String nome,
        String senha,
        String cargo
) {
}
