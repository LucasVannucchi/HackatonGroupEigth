package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.responses.UsuarioResponseDto;
import com.groupEight.TaskManagement.models.UsuarioModel;

public class UsuarioMapper {

    public static UsuarioResponseDto convertToUsuarioResponseDto (UsuarioModel model){
        return new UsuarioResponseDto(
                model.getId(),
                model.getNome(),
                model.getCargo(),
                model.getStatus()
        );
    }
}
