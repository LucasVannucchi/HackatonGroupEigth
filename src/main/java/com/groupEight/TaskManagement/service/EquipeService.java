package com.groupEight.TaskManagement.service;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioEmTimeDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.enuns.StatusEquipe;
import com.groupEight.TaskManagement.exception.EquipeNotFoundException;
import com.groupEight.TaskManagement.exception.UnableToUpdateEquipeException;
import com.groupEight.TaskManagement.mappers.EquipeMapper;
import com.groupEight.TaskManagement.mappers.UsuarioMapper;
import com.groupEight.TaskManagement.models.Equipe;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.EquipeRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EquipeService {

    EquipeRepository equipeRepository;
    EquipeMapper equipeMapper;
    UsuarioRepository usuarioRepository;

    public List<EquipeResponseDTO> getAll(){
        List<EquipeResponseDTO> equipes = equipeRepository.findAll().stream()
                .map(equipe -> equipeMapper.toResponseDTO(equipe))
                .toList();
        return equipes;
    }

    public EquipeResponseDTO createEquipe(EquipeRequestDTO dto){
        Equipe equipe = equipeMapper.toEntity(dto);
        equipeRepository.save(equipe);

        return equipeMapper.toResponseDTO(equipe);
    }

    public EquipeResponseDTO updateEquipe(Long id, EquipeRequestDTO dto){

        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        if (dto.nome() == null){
           throw new UnableToUpdateEquipeException("Impossivel atualizar equipe ");
        }
        if (dto.setor() == null){
            throw new UnableToUpdateEquipeException("Impossivel atualizar equipe ");
        }
        equipe.setSetor(dto.setor());
        equipe.setNome(dto.nome());
        return  equipeMapper.toResponseDTO(equipe);
    }

    public EquipeResponseDTO getById(long id){
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        return equipeMapper.toResponseDTO(equipe);
    }
    public EquipeResponseDTO encerrarEquipe(Long id){
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        equipe.setStatusEquipe(StatusEquipe.ENCERRADA);
        Equipe saved = equipeRepository.save(equipe);
        return equipeMapper.toResponseDTO(saved);
    }
    public void adicionarUsuario(UsuarioEmTimeDTO usuarioEmTimeDTO){
        Equipe equipe = equipeRepository.findById(usuarioEmTimeDTO.idEquipe()).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        UsuarioModel usuario = usuarioRepository.findById(usuarioEmTimeDTO.idUsuario()).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        equipe.adicionarUsuario(usuario);
    }
    public void removerUsuario(UsuarioEmTimeDTO usuarioEmTimeDTO){
        Equipe equipe = equipeRepository.findById(usuarioEmTimeDTO.idEquipe()).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        UsuarioModel usuario = usuarioRepository.findById(usuarioEmTimeDTO.idUsuario()).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        equipe.removerUsuario(usuario);
    }


}
