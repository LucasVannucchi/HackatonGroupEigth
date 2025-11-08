package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.responses.BaseResponse;
import com.groupEight.TaskManagement.DTO.responses.HistoricoTarefaResponseDTO;
import com.groupEight.TaskManagement.services.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoTarefasController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Buscar todos os históricos",
            description = "Retorna todos os históricos de tarefas cadastradas no sistema"
    )
    public List<HistoricoTarefaResponseDTO> getAllHistoricos(){
        return historicoService.getAllHistoricos();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Buscar histórico por ID da Tarefa",
            description = "Retorna o histórico de uma tarefa específica pelo seu ID"
    )
    public ResponseEntity<BaseResponse> getHistoricoById(@PathVariable Long id){
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Histórico encontrado com sucesso")
                .status(HttpStatus.OK)
                .data(historicoService.getHistoricoById(id))
                .build());
    }
}
