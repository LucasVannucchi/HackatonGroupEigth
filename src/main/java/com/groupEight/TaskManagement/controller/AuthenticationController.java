package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.UsuarioLoginRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Login", description = "Realiza o login do usuário")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(authenticationService.login(loginRequestDTO));
    }

    @PostMapping("/cadastrar/funcionario")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar funcionário", description = "Cadastra um novo funcionário")
    public ResponseEntity cadastrarFuncionario(@RequestBody UsuarioRequestDto request){
        log.info("Senha: " + request.senha());
        authenticationService.cadastrarFuncionario(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/cadastrar/gestor")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar gestor", description = "Cadastra um novo gestor")
    public ResponseEntity cadastrarGestor(@RequestBody UsuarioRequestDto request){
        log.info("Senha: " + request.senha());
        authenticationService.cadastrarGestor(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/cadastrar/supervisor")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar supervisor", description = "Cadastra um novo supervisor")
    public ResponseEntity cadastrarSupervisor(@RequestBody UsuarioRequestDto request){
        log.info("Senha: " + request.senha());
        authenticationService.cadastrarSupervisor(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/cadastrar/master")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar master", description = "Cadastra um novo master")
    public ResponseEntity cadastrarMaster(@RequestBody UsuarioRequestDto request){
        log.info("Senha: " + request.senha());
        authenticationService.cadastrarMaster(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }
}
