package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioETimeDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.enuns.Setor;
import com.groupEight.TaskManagement.enuns.StatusEquipe;
import com.groupEight.TaskManagement.exception.EntityNotFoundException;
import com.groupEight.TaskManagement.exception.EquipeNotFoundException;
import com.groupEight.TaskManagement.exception.UnableToUpdateEquipeException;
import com.groupEight.TaskManagement.exception.UserTeamException;
import com.groupEight.TaskManagement.mappers.EquipeMapper;
import com.groupEight.TaskManagement.models.Equipe;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.EquipeRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EquipeService {

    @Autowired
    EquipeRepository equipeRepository;
    @Autowired
    EquipeMapper equipeMapper;
    @Autowired
    UsuarioRepository usuarioRepository;


    public List<EquipeResponseDTO> getAll(){
        List<EquipeResponseDTO> equipes = equipeRepository.findAll().stream()
                .map(equipe -> equipeMapper.toResponseDTO(equipe))
                .toList();
        return equipes;
    }

    public List<EquipeResponseDTO> getAllOpenedEquipes(){
        List<EquipeResponseDTO> equipes = equipeRepository.findAll().stream().filter(e -> e.getStatusEquipe() != null)
                .filter(e -> e.getStatusEquipe().equals(StatusEquipe.OPERANTE))
                .map(equipe -> equipeMapper.toResponseDTO(equipe))
                .toList();
        return equipes;
    }

    public EquipeResponseDTO createEquipe(EquipeRequestDTO dto){
        Equipe equipe = equipeMapper.toEntity(dto);
        equipe.setStatusEquipe(StatusEquipe.OPERANTE);
        equipeRepository.save(equipe).setUsuarios(List.of()); {
        }

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
        equipeRepository.save(equipe);
        return  equipeMapper.toResponseDTO(equipe);
    }

    public EquipeResponseDTO getById(Long id){
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        return equipeMapper.toResponseDTO(equipe);
    }
    public EquipeResponseDTO encerrarEquipe(Long id){
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        equipe.setStatusEquipe(StatusEquipe.ENCERRADA);
        Equipe saved = equipeRepository.save(equipe);
        return equipeMapper.toResponseDTO(saved);
    }
    public void adicionarUsuario(UsuarioETimeDTO usuarioETimeDTO){
        Equipe equipe = equipeRepository.findById(usuarioETimeDTO.idEquipe()).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        UsuarioModel usuario = (UsuarioModel) usuarioRepository.findByEmail(usuarioETimeDTO.email()).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        if (equipe.getStatusEquipe().equals(StatusEquipe.ENCERRADA)){
            throw new UserTeamException("Equipe encerrada, impossivel adicionar usuário");
        }
        usuario.setEquipe(equipe);
        equipe.adicionarUsuario(usuario);
        equipeRepository.save(equipe);
        usuarioRepository.save(usuario);
    }
    public void removerUsuario(UsuarioETimeDTO usuarioETimeDTO){

        Equipe equipe = equipeRepository.findById(usuarioETimeDTO.idEquipe()).orElseThrow(() -> new EquipeNotFoundException("Equipe não encontrada"));
        UsuarioModel usuario = (UsuarioModel) usuarioRepository.findByEmail(usuarioETimeDTO.email()).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));
        equipe.removerUsuario(usuario);
        usuario.setEquipe(null);
        equipeRepository.save(equipe);
        usuarioRepository.save(usuario);
    }


}
