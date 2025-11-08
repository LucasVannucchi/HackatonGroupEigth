package com.groupEight.TaskManagement.DTO.requests;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AtribuirTarefaRequestDTO (
        Long idTarefa,
        String emailUsuario
){
}
