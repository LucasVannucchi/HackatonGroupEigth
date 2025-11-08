package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioETimeDTO;
import com.groupEight.TaskManagement.DTO.responses.EquipeResponseDTO;
import com.groupEight.TaskManagement.services.EquipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Criar equipe", description = "Cria uma nova equipe no sistema")
    public ResponseEntity<EquipeResponseDTO> createEquipe(@Valid @RequestBody EquipeRequestDTO dto) {
        EquipeResponseDTO novaEquipe = equipeService.createEquipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEquipe);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar equipe", description = "Atualiza os dados de uma equipe existente")
    public ResponseEntity<EquipeResponseDTO> updateEquipe(
            @PathVariable("id") Long id,
            @Valid @RequestBody EquipeRequestDTO dto) {

        EquipeResponseDTO equipeAtualizada = equipeService.updateEquipe(id, dto);
        return ResponseEntity.ok(equipeAtualizada);
    }

    @PatchMapping("/{id}/encerrar")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Encerrar equipe", description = "Marca uma equipe como encerrada")
    public ResponseEntity<EquipeResponseDTO> encerrarEquipe(@PathVariable("id") Long id) {
        EquipeResponseDTO encerrada = equipeService.encerrarEquipe(id);
        return ResponseEntity.ok(encerrada);
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Listar equipes", description = "Lista todas as equipes do sistema")
    public ResponseEntity<List<EquipeResponseDTO>> getAll() {
        List<EquipeResponseDTO> equipes = equipeService.getAll();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/equipesAtivas")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Listar equipes ativas", description = "Lista apenas as equipes que estão ativas")
    public ResponseEntity<List<EquipeResponseDTO>> getAllOpenedEquipes() {
        List<EquipeResponseDTO> equipes = equipeService.getAllOpenedEquipes();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Listar equipe por ID", description = "Busca uma equipe pelo seu ID")
    public ResponseEntity<EquipeResponseDTO> getEquipeById(@PathVariable("id") Long id) {
        EquipeResponseDTO equipe = equipeService.getById(id);
        return ResponseEntity.ok(equipe);
    }

    @PostMapping("/adicionar-usuario")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Adicionar usuário à equipe", description = "Adiciona um usuário a uma equipe existente")
    public ResponseEntity<Void> adicionarUsuario(@Valid @RequestBody UsuarioETimeDTO dto) {
        equipeService.adicionarUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/remover-usuario")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Remover usuário da equipe", description = "Remove um usuário de uma equipe")
    public ResponseEntity<Void> removerUsuario(@Valid @RequestBody UsuarioETimeDTO dto) {
        equipeService.removerUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}