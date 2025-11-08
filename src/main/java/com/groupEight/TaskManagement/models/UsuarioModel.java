package com.groupEight.TaskManagement.models;


import com.groupEight.TaskManagement.enuns.Permissoes;
import com.groupEight.TaskManagement.enuns.UsuarioStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;
    private String cargo;

    @Enumerated(EnumType.STRING)
    private Permissoes permissoes;

    @Enumerated(EnumType.STRING)
    private UsuarioStatus status;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefa = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoTarefa> historicoTarefa = new ArrayList<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.permissoes == Permissoes.Master){
            return List.of(new SimpleGrantedAuthority("ROLE_Master"));
        }
        if (this.permissoes == Permissoes.Gestor){
            return List.of(new SimpleGrantedAuthority("ROLE_Gestor"), new SimpleGrantedAuthority("ROLE_Funcionario"), new SimpleGrantedAuthority("ROLE_Supervisor"));
        }
        if(this.permissoes == Permissoes.Supervisor){
            return List.of(new SimpleGrantedAuthority("ROLE_Supervisor"), new SimpleGrantedAuthority("ROLE_Funcionario"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_Funcionario"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
