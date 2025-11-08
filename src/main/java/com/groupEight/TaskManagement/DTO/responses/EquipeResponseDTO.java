package com.groupEight.TaskManagement.DTO.responses;

import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.Setor;
import com.groupEight.TaskManagement.enuns.StatusEquipe;

import java.util.List;

public record EquipeResponseDTO(long id,
                                String nome,
                                Setor setor,
                                StatusEquipe statusEquipe,
                                List<UsuarioResponseDto> usuarios
                                ) {
}
