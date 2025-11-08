package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioEmTimeDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.services.EquipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    // 🔹 Criar equipe
    @PostMapping
    public ResponseEntity<EquipeResponseDTO> createEquipe(@Valid @RequestBody EquipeRequestDTO dto) {
        EquipeResponseDTO novaEquipe = equipeService.createEquipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEquipe);
    }

    // 🔹 Buscar todas as equipes
    @GetMapping
    public ResponseEntity<List<EquipeResponseDTO>> getAllEquipes() {
        List<EquipeResponseDTO> equipes = equipeService.getAll();
        return ResponseEntity.ok(equipes);
    }

    // 🔹 Buscar equipe por ID
    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> getEquipeById(@PathVariable Long id) {
        EquipeResponseDTO equipe = equipeService.getById(id);
        return ResponseEntity.ok(equipe);
    }

    // 🔹 Atualizar equipe (nome e setor)
    @PutMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> updateEquipe(
            @PathVariable Long id,
            @Valid @RequestBody EquipeRequestDTO dto) {

        EquipeResponseDTO equipeAtualizada = equipeService.updateEquipe(id, dto);
        return ResponseEntity.ok(equipeAtualizada);
    }

    // 🔹 Encerrar equipe (muda status para ENCERRADA)
    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<EquipeResponseDTO> encerrarEquipe(@PathVariable Long id) {
        EquipeResponseDTO encerrada = equipeService.encerrarEquipe(id);
        return ResponseEntity.ok(encerrada);
    }

    // 🔹 Adicionar usuário à equipe
    @PostMapping("/adicionar-usuario")
    public ResponseEntity<Void> adicionarUsuario(@Valid @RequestBody UsuarioEmTimeDTO dto) {
        equipeService.adicionarUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 🔹 Remover usuário da equipe
    @DeleteMapping("/remover-usuario")
    public ResponseEntity<Void> removerUsuario(@Valid @RequestBody UsuarioEmTimeDTO dto) {
        equipeService.removerUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}