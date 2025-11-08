package com.groupEight.TaskManagement.mappers;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.models.Equipe;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class EquipeMapper {

    UsuarioMapper usuarioMapper;

    public EquipeResponseDTO toResponseDTO(Equipe equipe){

        List<UsuarioResponseDto> usuariosResponseDto = equipe.getUsuarios()
                .stream()
                .map((usuario) -> UsuarioMapper.convertToUsuarioResponseDto(usuario) ).toList();

        EquipeResponseDTO dto = new EquipeResponseDTO(
                equipe.getId(),
                equipe.getNome(),
                equipe.getSetor(),
                equipe.getStatusEquipe(),
                usuariosResponseDto
        );
        return dto;
    }

    public Equipe toEntity(EquipeRequestDTO dto){
        Equipe equipe = new Equipe();
        equipe.setNome(dto.nome());
        equipe.setSetor(dto.setor());

        return equipe;
    }
}
