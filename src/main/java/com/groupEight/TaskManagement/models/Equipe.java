package com.groupEight.TaskManagement.models;

import com.groupEight.TaskManagement.enuns.Setor;
import com.groupEight.TaskManagement.enuns.StatusEquipe;
import com.groupEight.TaskManagement.exception.UserTeamException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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

    private StatusEquipe statusEquipe;

    //Ajustar relação posteriormente
    private List<UsuarioModel> usuarios;

    public void adicionarUsuario(UsuarioModel usuario) {
        if (usuarios.contains(usuario)) {
            throw new UserTeamException("Usuário já registrado no time");
        }
        usuarios.add(usuario);
    }

    public void removerUsuario(UsuarioModel usuario) {
        if (!usuarios.contains(usuario)) {
            throw new UserTeamException("Usuário não está registrado no time");
        }
        usuarios.remove(usuario);
    }
}
