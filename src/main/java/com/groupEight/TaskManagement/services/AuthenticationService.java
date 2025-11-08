package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.requests.UsuarioLoginRequestDTO;

import com.groupEight.TaskManagement.DTO.requests.UsuarioRequest;
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

    public void cadastrar(UsuarioRequest request){
        log.info("Senha recebida do DTO: "+ request.senha());
        UsuarioModel usuario = UsuarioModel.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(new BCryptPasswordEncoder().encode(request.senha()))
                .permissoes(request.permissoes())
                .cargo(request.cargo())
                .status(request.status())
                .build();
        usuarioRepository.save(usuario);
    }
}
