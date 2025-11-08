package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.EquipeRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.UsuarioETimeDTO;
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

    @PostMapping
    public ResponseEntity<EquipeResponseDTO> createEquipe(@Valid @RequestBody EquipeRequestDTO dto) {
        EquipeResponseDTO novaEquipe = equipeService.createEquipe(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEquipe);
    }

    @GetMapping
    public ResponseEntity<List<EquipeResponseDTO>> getAll() {
        List<EquipeResponseDTO> equipes = equipeService.getAll();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/equipesAtivas")
    public ResponseEntity<List<EquipeResponseDTO>> getAllOpenedEquipes() {
        List<EquipeResponseDTO> equipes = equipeService.getAllOpenedEquipes();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> getEquipeById(@PathVariable("id") Long id) {
        EquipeResponseDTO equipe = equipeService.getById(id);
        return ResponseEntity.ok(equipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> updateEquipe(
            @PathVariable("id") Long id,
            @Valid @RequestBody EquipeRequestDTO dto) {

        EquipeResponseDTO equipeAtualizada = equipeService.updateEquipe(id, dto);
        return ResponseEntity.ok(equipeAtualizada);
    }
    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<EquipeResponseDTO> encerrarEquipe(@PathVariable("id") Long id) {
        EquipeResponseDTO encerrada = equipeService.encerrarEquipe(id);
        return ResponseEntity.ok(encerrada);
    }

    @PostMapping("/adicionar-usuario")
    public ResponseEntity<Void> adicionarUsuario(@Valid @RequestBody UsuarioETimeDTO dto) {
        equipeService.adicionarUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/remover-usuario")
    public ResponseEntity<Void> removerUsuario(@Valid @RequestBody UsuarioETimeDTO dto) {
        equipeService.removerUsuario(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}