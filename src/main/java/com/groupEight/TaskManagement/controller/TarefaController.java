package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.AtribuirTarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.BaseResponse;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import com.groupEight.TaskManagement.services.TarefaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cria uma nova tarefa")
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
    @Operation(summary = "Busca todas as tarefas de um Usuario")
    public ResponseEntity<BaseResponse> buscarTodasTarefas(@RequestParam String emailUsuario) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarTodasTarefas(emailUsuario))
                .build());
    }

    // 🔹 Buscar todas do usuario
    @GetMapping("/all/minhasTarefas")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um Usuario")
    public ResponseEntity<BaseResponse> buscarTodasTarefasUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarTodasTarefasUsuario(userDetails))
                .build());
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca uma tarefa")
    public ResponseEntity<BaseResponse> buscarTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa encontrada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarById(id))
                .build());
    }

    // 🔹 Buscar por status (geral e do usuário)
    @GetMapping("/status/all/{status}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas por Status")
    public ResponseEntity<BaseResponse> buscarPorStatus(@PathVariable TipoStatus status) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorStatus(status))
                .build());
    }

    @GetMapping("/status/{status}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um Usuario por Status")
    public ResponseEntity<BaseResponse> buscarPorStatusFuncionario(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable TipoStatus status) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorStatusFuncionario(userDetails, status))
                .build());
    }

    // 🔹 Buscar por prioridade (geral e do usuário)
    @GetMapping("/prioridade/all/{prioridade}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas por Prioridade!")
    public ResponseEntity<BaseResponse> buscarPorPrioridade(@PathVariable TipoPrioridade prioridade) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorPrioridade(prioridade))
                .build());
    }

    @GetMapping("/prioridade/{prioridade}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Busca todas as tarefas de um Usuario por Prioridade")
    public ResponseEntity<BaseResponse> buscarPorPrioridadeFuncionario(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable TipoPrioridade prioridade) {

        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefas encontradas com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.buscarPorPrioridadeFuncionario(userDetails, prioridade))
                .build());
    }

    // 🔹 PATCH endpoints (corrigidos)
    @PatchMapping("/atribuir")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atribui uma tarefa")
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
    @Operation(summary = "Inicia uma tarefa")
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
    @Operation(summary = "Analisa uma tarefa")
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
    @Operation(summary = "Conclui uma tarefa")
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
    @Operation(summary = "Reabre uma tarefa")
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
    @Operation(summary = "Cancela uma tarefa")
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
    @Operation(summary = "Atualiza uma tarefa")
    public ResponseEntity<BaseResponse> updateTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO tarefaRequestDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa atualizada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.atualizarTarefa(id, tarefaRequestDTO, userDetails))
                .build());
    }
}
