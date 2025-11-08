package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.usuario.*;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseGestorFeriasDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseGetAllDto;
import com.groupEight.TaskManagement.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/verTodos")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Ver todos os usuários", description = "Lista todos os usuários do sistema")
    public ResponseEntity<List<UsuarioResponseGetAllDto>> verTodosUsuarios(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.getAllUsers(userDetails));
    }

    @PatchMapping("/atualizar")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar usuário", description = "Atualiza informações de um usuário existente")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@RequestBody UpdateUsuarioRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.updateUser(requestDto,userDetails));
    }

    @PutMapping("/ferias")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Colocar usuário de férias", description = "Define um usuário como estando de férias")
    public ResponseEntity<UsuarioResponseDto> colocarDeFerias(
            @RequestBody @Valid UsuarioRequestFerias requestFerias,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UsuarioResponseDto response = usuarioService.colocarDeFerias(requestFerias, userDetails);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/gestor/ferias")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Colocar gestor de férias", description = "Define um gestor como estando de férias")
    public ResponseEntity<UsuarioResponseGestorFeriasDto> colocarGestorDeFerias(
            @RequestBody @Valid UsuarioRequestSupervisorFerias requestFerias,
            @AuthenticationPrincipal UserDetails userDetails) {

        UsuarioResponseGestorFeriasDto response = usuarioService.colocarGestorDeFerias(requestFerias, userDetails);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/desligar")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Desligar usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<UsuarioResponseDto> desligarUsuario(
            @RequestBody @Valid UsuarioRequestDesligamento requestDesligamento,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UsuarioResponseDto response = usuarioService.desligarUsuario(requestDesligamento, userDetails);
        return ResponseEntity.ok(response);
    }
}
