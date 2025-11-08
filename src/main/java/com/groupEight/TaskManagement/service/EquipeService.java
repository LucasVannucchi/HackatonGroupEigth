package com.groupEight.TaskManagement.service;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.mappers.EquipeMapper;
import com.groupEight.TaskManagement.models.Equipe;
import com.groupEight.TaskManagement.repository.EquipeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EquipeService {

    EquipeRepository equipeRepository;
    EquipeMapper equipeMapper;

    public EquipeResponseDTO createEquipe(EquipeRequestDTO dto){
        Equipe equipe = equipeMapper.toEntity(dto);
        equipeRepository.save(equipe);

        return equipeMapper.toResponseDTO(equipe);
    }

    public EquipeRequestDTO updateEquipe(Long id, EquipeRequestDTO){

        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> );

    }

}
