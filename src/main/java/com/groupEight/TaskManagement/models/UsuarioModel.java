package com.groupEight.TaskManagement.models;


import com.groupEight.TaskManagement.enums.Permissoes;
import com.groupEight.TaskManagement.enums.UsuarioStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private Permissoes permissoes;
    private UsuarioStatus status;

    /*@ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe; */


}
