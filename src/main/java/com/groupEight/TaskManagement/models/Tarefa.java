package com.groupEight.TaskManagement.models;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "proprietario_tarefa", nullable = false)
    private Usuario usuario;

    @Column(name = "status", nullable = false)
    private TipoStatus status;

    @Column(name = "prioridade", nullable = false)
    private TipoPrioridade prioridade;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_prevista", nullable = false)
    private LocalDateTime dataPrevista;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;
}
