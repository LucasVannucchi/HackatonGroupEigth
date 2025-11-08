package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import com.groupEight.TaskManagement.mappers.UsuarioMapper;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
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


    //metodo que vai ser usado no controller, pelo gestor, onde pega o id e confere se é um gestor ou surpervisor
    public List<UsuarioResponseDto> getAllUsers(UUID id){

        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findById(id);

        if(usuarioEncontrado.isPresent() && (usuarioEncontrado.get().getPermissoes()== Permissoes.Gestor ||  usuarioEncontrado.get().getPermissoes()== Permissoes.Supervisor)){
            return usuarioRepository.findAll().stream()
                    .map(UsuarioMapper::convertToUsuarioResponseDto)
                    .toList();
        }
        return Collections.emptyList();
    }


}
