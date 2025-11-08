package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.usuario.*;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.enuns.Acoes;
import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import com.groupEight.TaskManagement.exception.EntityNotFoundException;
import com.groupEight.TaskManagement.exception.UnauthorizedException;
import com.groupEight.TaskManagement.mappers.UsuarioMapper;
import com.groupEight.TaskManagement.models.Tarefa;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.TarefaRepository;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;


    //metodo que vai ser usado no controller, pelo gestor, onde pega o id e confere se é um gestor ou surpervisor
    public List<UsuarioResponseGetAllDto> getAllUsers(UserDetails userDetails){

        UsuarioModel usuario = getUsuarioModel(userDetails);

        if(usuario.getPermissoes()==Permissoes.Gestor){
            return usuarioRepository.findAll().stream()
                    .map(UsuarioMapper::convertTousuarioResponseGetAllDto)
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

    public UsuarioResponseDto colocarDeFerias(UsuarioRequestFerias requestFerias, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);

        if(usuario.getPermissoes()==Permissoes.Gestor){

            UsuarioModel usuarioModel = (UsuarioModel) usuarioRepository.findByEmail(requestFerias.email()).orElseThrow(()->new EntityNotFoundException("Usuario não encontrado"));
            List<TipoStatus> listaStatus = List.of(TipoStatus.Em_Andamento,TipoStatus.Pendente);

            List<Tarefa> listaTarefas = tarefaRepository.findTarefasByUsuarioAndDataPrevistaBetweenAndStatusIn(usuarioModel.getId(),requestFerias.dataInicio(),requestFerias.dataFinal(),listaStatus);

            listaTarefas.forEach(tarefa -> tarefa.setAcao(Acoes.Realocar));
            listaTarefas.forEach(tarefa -> tarefa.setUsuario(usuario));
            tarefaRepository.saveAll(listaTarefas);

            usuarioModel.setStatus(UsuarioStatus.Ferias);
            usuarioRepository.save(usuarioModel);

            return  UsuarioMapper.convertToUsuarioResponseDto(usuarioModel);
        }
        throw new UnauthorizedException("Apenas Gestores podem registrar férias");
    }

    public UsuarioResponseDto desligarUsuario(UsuarioRequestDesligamento requestDesligamento, UserDetails userDetails){
        UsuarioModel usuario = getUsuarioModel(userDetails);

        if(usuario.getPermissoes()==Permissoes.Gestor){
            UsuarioModel usuarioModel = (UsuarioModel) usuarioRepository.findByEmail(requestDesligamento.email()).orElseThrow(()->new EntityNotFoundException("Usuario não encontrado"));
            List<TipoStatus> listaStatus = List.of(TipoStatus.Em_Andamento,TipoStatus.Pendente);

            List<Tarefa> listaTarefas = tarefaRepository.findTarefasAposDesligamentoComStatus(usuarioModel.getId(),requestDesligamento.dataDesligamento(),listaStatus);
            listaTarefas.forEach(tarefa -> tarefa.setAcao(Acoes.Realocar));
            listaTarefas.forEach(tarefa -> tarefa.setUsuario(usuario));

            usuarioModel.setStatus(UsuarioStatus.Desligado);
            usuarioRepository.save(usuarioModel);
        }
        throw new UnauthorizedException("Apenas Gestores podem realizar o desligamento de um funcionario");
    }





}
