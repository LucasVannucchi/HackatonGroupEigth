package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.enuns.Setor;

import java.util.List;

public record EquipeResponseDTO(long id,
                                String nome,
                                Setor setor,
                                List<UsuarioResponseDto> usuarios
                                ) {
}
