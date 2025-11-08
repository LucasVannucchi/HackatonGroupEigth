package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.usuario.UsuarioRequestDto;
import com.groupEight.TaskManagement.DTO.responses.usuario.UsuarioResponseDto;
import com.groupEight.TaskManagement.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<List<UsuarioResponseDto>> verTodosUsuarios(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioService.getAllUsers(id));
    }

}
