package com.groupEight.TaskManagement.models;


import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cargo")
    private String cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    private Permissoes permissoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "ususario_status")
    private UsuarioStatus status;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefa;

    /*@ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe; */


}
