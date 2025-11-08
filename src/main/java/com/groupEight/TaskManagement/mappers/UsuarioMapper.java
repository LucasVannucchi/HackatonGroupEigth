package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.usuario.UpdateUsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseGestorFeriasDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseGetAllDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static UsuarioResponseDto convertToUsuarioResponseDto(UsuarioModel model) {
        return new UsuarioResponseDto(
                model.getId(),
                model.getNome(),
                model.getEmail(),
                model.getCargo(),
                model.getStatus(),
                model.getPermissoes()
        );
    }

    public static UsuarioResponseGestorFeriasDto convertToGestorFeriasResponseDto(
            UsuarioModel gestor,
            UsuarioModel substituto,
            LocalDateTime dataInicio,
            LocalDateTime dataFinal
    ) {
        return new UsuarioResponseGestorFeriasDto(
                substituto.getEmail(),
                substituto.getPermissoes(),
                gestor.getEmail()

        );
    }

    public static UsuarioResponseGetAllDto convertToUsuarioResponseGetAllDto(UsuarioModel model) {
        String equipe;
        if (model.getEquipe() != null) {
            equipe = model.getEquipe().getNome();
        } else {
            equipe = "Usuário não está associado a nenhuma equipe.";
        }

        return new UsuarioResponseGetAllDto(
                model.getId(),
                model.getNome(),
                model.getEmail(),
                new BCryptPasswordEncoder().encode(model.getSenha()),
                model.getCargo(),
                model.getStatus(),
                model.getPermissoes(),
                equipe
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
