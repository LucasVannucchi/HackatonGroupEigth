package com.groupEight.TaskManagement.models;

import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Table(name= "tb_tarefas")
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TipoStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade")
    private TipoPrioridade prioridade;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_prevista")
    private LocalDateTime dataPrevista;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;
}
