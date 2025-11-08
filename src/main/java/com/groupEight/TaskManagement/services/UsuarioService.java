package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.usuario.UpdateUsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestFerias;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import com.groupEight.TaskManagement.mappers.UsuarioMapper;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //metodo que vai ser usado no controller, pelo gestor, onde pega o id e confere se é um gestor ou surpervisor
    public List<UsuarioResponseDto> getAllUsers(UserDetails userDetails){

        UsuarioModel usuario = getUsuarioModel(userDetails);

        if(usuario.getPermissoes()==Permissoes.Gestor){
            return usuarioRepository.findAll().stream()
                    .map(UsuarioMapper::convertToUsuarioResponseDto)
                    .toList();
        }
        return Collections.emptyList();
    }

    private UsuarioModel getUsuarioModel(UserDetails userDetails){
        return (UsuarioModel) usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }

    public UsuarioResponseDto updateUser(UpdateUsuarioRequestDto requestDto, UserDetails userDetails) {

        UsuarioModel usuario = getUsuarioModel(userDetails);

        if (usuario.getPermissoes() == Permissoes.Gestor) {

            UsuarioModel usuarioModel = (UsuarioModel) usuarioRepository.findByEmail(requestDto.email())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            UsuarioMapper.updateUsuarioFromDto(requestDto, usuarioModel);

            usuarioRepository.save(usuarioModel);

            return UsuarioMapper.convertToUsuarioResponseDto(usuarioModel);
        }
        throw new AccessDeniedException("Apenas gestores podem atualizar informações de outros usuários.");
    }

//    public UsuarioResponseDto colocarDeFerias(UsuarioRequestFerias requestFerias, UserDetails userDetails){
//        UsuarioModel usuario = getUsuarioModel(userDetails);
//
//        if(usuario.getPermissoes()==Permissoes.Gestor){
//            List<Tarefa> lista = usuario.getTarefa();
//
//        }
//    }





}
