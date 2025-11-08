package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.responses.BaseResponse;
import com.groupEight.TaskManagement.DTO.responses.HistoricoTarefaResponseDTO;
import com.groupEight.TaskManagement.services.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoTarefasController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    @Operation(summary = "Buscar todos os históricos")
    public List<HistoricoTarefaResponseDTO> getAllHistoricos(){
        return historicoService.getAllHistoricos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar histórico por ID da Tarefa")
    public ResponseEntity<BaseResponse> getHistoricoById(@PathVariable Long id){
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Histórico encontrado com sucesso")
                .status(HttpStatus.OK)
                .data(historicoService.getHistoricoById(id))
                .build());
    }
}
