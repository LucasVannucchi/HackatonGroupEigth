package com.groupEight.TaskManagement.repository;

import com.groupEight.TaskManagement.models.HistoricoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoTarefa, Long> {
}
