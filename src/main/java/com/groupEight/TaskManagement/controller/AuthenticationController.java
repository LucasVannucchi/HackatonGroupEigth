package com.groupEight.TaskManagement.controller;


import com.groupEight.TaskManagement.DTO.requests.UsuarioLoginRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.services.AuthenticationService;
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
    public ResponseEntity<String> login(@RequestBody UsuarioLoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok().body(authenticationService.login(loginRequestDTO));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody UsuarioRequestDto request){
        log.info("Senha: " + request.senha());
        authenticationService.cadastrar(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }
}
