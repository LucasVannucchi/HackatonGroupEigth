package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.DTO.responses.HistoricoTarefaResponseDTO;
import com.groupEight.TaskManagement.mappers.HistoricoMapper;
import com.groupEight.TaskManagement.repository.HistoricoRepository;
import com.groupEight.TaskManagement.repository.TarefaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;


    public List<HistoricoTarefaResponseDTO> getAllHistoricos(){
        return historicoRepository.findAll()
                .stream()
                .map(HistoricoMapper::toHistoricoResponseDTO)
                .collect(Collectors.toList());
    }

    public List<HistoricoTarefaResponseDTO> getHistoricoById(Long id){

        return historicoRepository.findByTarefa_Id(id)
                .stream()
                .map(HistoricoMapper::toHistoricoResponseDTO)
                .collect(Collectors.toList());
    }

}
