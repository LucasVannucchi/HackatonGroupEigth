package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.UsuarioLoginRequestDTO;

import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired TokenServices tokenServices;

    public String login(UsuarioLoginRequestDTO loginRequestDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token =  tokenServices.generateToken((UsuarioModel) auth.getPrincipal());

        return token;
    }

    public void cadastrarFuncionario(UsuarioRequestDto request){
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(new BCryptPasswordEncoder().encode(request.senha()))
                .permissoes(Permissoes.Funcionario)
                .cargo(request.cargo())
                .status(UsuarioStatus.Ativo)
                .build();
        usuarioRepository.save(usuario);
    }
    public void cadastrarSupervisor(UsuarioRequestDto request){
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(new BCryptPasswordEncoder().encode(request.senha()))
                .permissoes(Permissoes.Supervisor)
                .cargo(request.cargo())
                .status(UsuarioStatus.Ativo)
                .build();
        usuarioRepository.save(usuario);
    }
    public void cadastrarGestor(UsuarioRequestDto request){
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(new BCryptPasswordEncoder().encode(request.senha()))
                .permissoes(Permissoes.Gestor)
                .cargo(request.cargo())
                .status(UsuarioStatus.Ativo)
                .build();
        usuarioRepository.save(usuario);
    }
    public void cadastrarMaster(UsuarioRequestDto request){
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(new BCryptPasswordEncoder().encode(request.senha()))
                .permissoes(Permissoes.Master)
                .cargo(request.cargo())
                .status(UsuarioStatus.Ativo)
                .build();
        usuarioRepository.save(usuario);
    }


}
