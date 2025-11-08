package com.groupEight.TaskManagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.groupEight.TaskManagement.enuns.Acoes;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @Enumerated(EnumType.STRING)
    private TipoStatus status;

    @Enumerated(EnumType.STRING)
    private Acoes acao;

    @Enumerated(EnumType.STRING)
    private TipoPrioridade prioridade;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevista;
    private LocalDateTime dataFim;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HistoricoTarefa> historicos = new ArrayList<>();
}