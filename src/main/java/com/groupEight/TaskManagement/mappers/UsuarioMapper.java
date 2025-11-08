package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.usuario.UpdateUsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioMapper {

    public static UsuarioResponseDto convertToUsuarioResponseDto (UsuarioModel model){
        return new UsuarioResponseDto(
                model.getId(),
                model.getNome(),
                model.getEmail(),
                new BCryptPasswordEncoder().encode(model.getSenha()),
                model.getCargo(),
                model.getStatus(),
                model.getPermissoes()
        );
    }

    public static void updateUsuarioFromDto(UpdateUsuarioRequestDto dto, UsuarioModel usuario) {

        if (dto.nome() != null && !dto.nome().isBlank()) {
            usuario.setNome(dto.nome());
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            usuario.setEmail(dto.email());
        }

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(dto.senha());
        }

        if (dto.cargo() != null && !dto.cargo().isBlank()) {
            usuario.setCargo(dto.cargo());
        }

        if (dto.permissoes() != null) {
            usuario.setPermissoes(dto.permissoes());
        }
    }
}
