package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.AtribuirTarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.BaseResponse;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private final TarefaService tarefaService;

    // 🔹 Criar tarefa
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cria uma nova tarefa", description = "Cria uma nova tarefa no sistema")
    public ResponseEntity<BaseResponse> createTarefa(
            @RequestBody TarefaRequestDTO tarefaRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa criada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.criarTarefa(tarefaRequestDTO, userDetails))
                .build());
    }

    // 🔹 Buscar todas
    @GetMapping("/all")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um usuário", description = "Lista todas as tarefas do sistema ou de um usuário específico")
    public ResponseEntity<BaseResponse> buscarTodasTarefas(@RequestParam String emailUsuario) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarTodasTarefas(emailUsuario))
                .build());
    }

    @GetMapping("/all/minhasTarefas")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as minhas tarefas", description = "Lista todas as tarefas do usuário logado")
    public ResponseEntity<BaseResponse> buscarTodasTarefasUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarTodasTarefasUsuario(userDetails))
                .build());
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca uma tarefa", description = "Busca uma tarefa pelo ID")
    public ResponseEntity<BaseResponse> buscarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa encontrada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarById(id))
                .build());
    }

    @GetMapping("/status/all/{status}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas por Status", description = "Filtra tarefas pelo status (geral)")
    public ResponseEntity<BaseResponse> buscarPorStatus(@PathVariable TipoStatus status) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorStatus(status))
                .build());
    }

    @GetMapping("/status/{status}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um usuário por Status", description = "Filtra tarefas do usuário logado pelo status")
    public ResponseEntity<BaseResponse> buscarPorStatusFuncionario(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable TipoStatus status) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorStatusFuncionario(userDetails, status))
                .build());
    }

    @GetMapping("/prioridade/all/{prioridade}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas por Prioridade", description = "Filtra tarefas pelo nível de prioridade (geral)")
    public ResponseEntity<BaseResponse> buscarPorPrioridade(@PathVariable TipoPrioridade prioridade) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorPrioridade(prioridade))
                .build());
    }

    @GetMapping("/prioridade/{prioridade}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um usuário por Prioridade", description = "Filtra tarefas do usuário logado pelo nível de prioridade")
    public ResponseEntity<BaseResponse> buscarPorPrioridadeFuncionario(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable TipoPrioridade prioridade) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorPrioridadeFuncionario(userDetails, prioridade))
                .build());
    }

    // 🔹 PATCH endpoints
    @PatchMapping("/atribuir")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atribui uma tarefa", description = "Atribui uma tarefa a um usuário")
    public ResponseEntity<BaseResponse> atribuirTarefa(
            @RequestBody AtribuirTarefaRequestDTO dto,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa atribuída com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.atribuirTarefa(dto, userDetails))
                .build());
    }

    @PatchMapping("/iniciar/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Inicia uma tarefa", description = "Muda o status da tarefa para iniciada")
    public ResponseEntity<BaseResponse> iniciarTarefa(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa iniciada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.iniciarTarefa(id, userDetails))
                .build());
    }

    @PatchMapping("/analisar/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Analisa uma tarefa", description = "Muda o status da tarefa para análise")
    public ResponseEntity<BaseResponse> analiseTarefa(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa analisada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.analiseTarefa(id, userDetails))
                .build());
    }

    @PatchMapping("/concluir/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Conclui uma tarefa", description = "Muda o status da tarefa para concluída")
    public ResponseEntity<BaseResponse> concluirTarefa(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa concluída com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.concluirTarefa(id, userDetails))
                .build());
    }

    @PatchMapping("/reabrir/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Reabre uma tarefa", description = "Reabre uma tarefa concluída ou cancelada")
    public ResponseEntity<BaseResponse> reabrirTarefa(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa reaberta com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.reabrirTarefa(id, userDetails))
                .build());
    }

    @PatchMapping("/cancelar/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cancela uma tarefa", description = "Cancela uma tarefa em andamento")
    public ResponseEntity<BaseResponse> cancelarTarefa(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa cancelada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.cancelarTarefa(id, userDetails))
                .build());
    }

    @PatchMapping("/atualizar/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualiza uma tarefa", description = "Atualiza dados de uma tarefa existente")
    public ResponseEntity<BaseResponse> updateTarefa(
            @PathVariable Long id,
            @RequestBody TarefaRequestDTO tarefaRequestDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa atualizada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.atualizarTarefa(id, tarefaRequestDTO, userDetails))
                .build());
    }
}
