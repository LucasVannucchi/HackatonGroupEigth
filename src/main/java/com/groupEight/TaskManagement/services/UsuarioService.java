package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.responses.UsuarioResponseDto;
import com.groupEight.TaskManagement.mappers.UsuarioMapper;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //apenas o Gestor tem a permição de dar um get allUsers, esse ,metodo vai ser puxado no controller com um ID

    public List<UsuarioResponseDto> getAllUsers(UUID id){
        if(usuarioRepository.existsById(id)){
            return usuarioRepository.findAll().stream()
                    .map(UsuarioMapper::convertToUsuarioResponseDto)
                    .toList();
        }
        return Collections.emptyList();
    }

}
