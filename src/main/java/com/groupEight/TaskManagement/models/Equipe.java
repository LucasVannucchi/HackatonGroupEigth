package com.groupEight.TaskManagement.models;

import com.groupEight.TaskManagement.enuns.Setor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_equipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "nome_equipe")
    private String nome;

    @Column(name = "setor")
    @NotNull
    private Setor setor;

    //Ajustar relação posteriormente
    private List<UsuarioModel> usuarios;

}
