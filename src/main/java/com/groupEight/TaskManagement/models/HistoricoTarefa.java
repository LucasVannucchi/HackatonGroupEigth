package com.groupEight.TaskManagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.groupEight.TaskManagement.enuns.Acoes;
import com.groupEight.TaskManagement.enuns.TipoPrioridade;
import com.groupEight.TaskManagement.enuns.TipoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_tarefas_historico")
public class HistoricoTarefa {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_alteracao")
    private UsuarioModel usuarioAlteracao;

    private String comentario;

    private LocalDateTime dataAtualizacao;
}
