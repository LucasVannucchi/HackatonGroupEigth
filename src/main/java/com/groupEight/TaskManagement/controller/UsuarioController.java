package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.usuario.UpdateUsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.models.UsuarioModel;
import com.groupEight.TaskManagement.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/verTodos")
    public ResponseEntity<List<UsuarioResponseDto>> verTodosUsuarios( @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.getAllUsers(userDetails));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@RequestBody UpdateUsuarioRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(usuarioService.updateUser(requestDto,userDetails));
    }

}
