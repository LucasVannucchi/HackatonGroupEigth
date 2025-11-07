package com.groupEight.TaskManagement.controller;


import com.groupEight.TaskManagement.DTO.requests.UsuarioLoginRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioRequest;
import com.groupEight.TaskManagement.services.AuthenticationService;
import com.groupEight.TaskManagement.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(UsuarioLoginRequestDTO loginRequestDTO){
        authenticationService.login(loginRequestDTO);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    public ResponseEntity cadastrar(UsuarioRequest request){
        authenticationService.cadastrar(request);
        URI uri = UriComponentsBuilder.fromPath("/auth/cadastrar").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }
}
