package com.groupEight.TaskManagement.services;

import com.groupEight.TaskManagement.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;


}
