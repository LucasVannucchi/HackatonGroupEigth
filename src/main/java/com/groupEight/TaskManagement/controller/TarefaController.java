package com.groupEight.TaskManagement.controller;

import com.groupEight.TaskManagement.DTO.requests.TarefaRequestDTO;
import com.groupEight.TaskManagement.DTO.responses.BaseResponse;
import com.groupEight.TaskManagement.mappers.TarefaMapper;
import com.groupEight.TaskManagement.repository.TarefasRepository;
import com.groupEight.TaskManagement.services.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;


@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Cria uma nova tarefa")
    public ResponseEntity<BaseResponse> createTarefa(@RequestBody TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.ok(BaseResponse.builder()
                .message("Tarefa criada com sucesso")
                .status(HttpStatus.OK)
                .data(tarefaService.criarTarefa(tarefaRequestDTO))
                .build());
    }
}
