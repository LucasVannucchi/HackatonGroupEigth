package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.usuario.*;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseGetAllDto;
import com.groupEight.TaskManagement.services.UsuarioService;
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
    public ResponseEntity<List<UsuarioResponseGetAllDto>> verTodosUsuarios(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.getAllUsers(userDetails));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@RequestBody UpdateUsuarioRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.updateUser(requestDto,userDetails));
    }

    @PutMapping("/ferias")
    public ResponseEntity<UsuarioResponseDto> colocarDeFerias(
            @RequestBody UsuarioRequestFerias requestFerias,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UsuarioResponseDto response = usuarioService.colocarDeFerias(requestFerias, userDetails);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/desligar")
    public ResponseEntity<UsuarioResponseDto> desligarUsuario(
            @RequestBody UsuarioRequestDesligamento requestDesligamento,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UsuarioResponseDto response = usuarioService.desligarUsuario(requestDesligamento, userDetails);
        return ResponseEntity.ok(response);
    }

}
