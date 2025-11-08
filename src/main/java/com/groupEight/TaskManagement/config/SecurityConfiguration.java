package com.groupEight.TaskManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Desabilita CSRF (recomendado para APIs REST)
                .csrf(csrf -> csrf.disable())
                // Define o uso de sessão como stateless (sem sessão de usuário)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Permite o uso do console do H2
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // Configura rotas públicas e privadas
                .authorizeHttpRequests(authorization -> authorization
                        // Endpoints públicos
                        //.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        // Endpoints privados
                        .requestMatchers("/auth/cadastrar/master").hasRole("Master")
                        .requestMatchers("/auth/cadastrar/gestor").hasRole("Master")
                        .requestMatchers("/auth/cadastrar/supervisor").hasRole("Gestor")
                        .requestMatchers("/auth/cadastrar/funcionario").hasRole("Supervisor")
                        .requestMatchers("/usuario/gestor/ferias").hasRole("Gestor")
                        .requestMatchers("/usuario/ferias").hasRole("Supervisor")
                        .requestMatchers("/usuario/atualizar").hasRole("Supervisor")
                        .requestMatchers("/usuario/verTodos").hasRole("Master")
                        .requestMatchers("/equipe/**").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.POST, "/tarefas").hasRole("Supervisor")
                        .requestMatchers("/tarefas/reabrir/**").hasRole("Supervisor")
                        .requestMatchers("/tarefas/iniciar/**").hasRole("Funcionario")
                        .requestMatchers("/tarefas/concluir/**").hasRole("Supervisor")
                        .requestMatchers("/tarefas/cancelar/**").hasRole("Supervisor")
                        .requestMatchers("/tarefas/atualizar/**").hasRole("Supervisor")
                        .requestMatchers("/tarefas/atribuir").hasRole("Supervisor")
                        .requestMatchers("/tarefas/analisar/**").hasRole("Funcionario")
                        .requestMatchers(HttpMethod.GET, "/tarefas/{id}").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.GET, "/tarefas/status/{status}").hasRole("Funcionario")
                        .requestMatchers(HttpMethod.GET, "/tarefas/status/all/**").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.GET, "/tarefas/prioridade/{prioridade}").hasRole("Funcionario")
                        .requestMatchers(HttpMethod.GET, "/tarefas/prioridade/all/{prioridade}").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.GET, "/tarefas/all").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.GET, "/tarefas/all/minhasTarefas").hasRole("Funcionario")
                        .requestMatchers(HttpMethod.GET, "/historico/**").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.POST, "/equipes").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.PUT, "/equipes/{id}").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.POST, "/equipes/adicionar-usuario").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.PATCH, "/equipes/{id}/encerrar").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.GET, "/equipes/equipesAtivas").hasRole("Supervisor")
                        .requestMatchers(HttpMethod.DELETE, "/equipes/remover-usuario").hasRole("Supervisor")

                        // Endpoints necessários para o Swagger funcionar
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**"
                        ).permitAll()
                        // Todas as outras rotas exigem autenticação
                        .anyRequest().authenticated()
                )
                // Adiciona o filtro JWT antes do filtro padrão de autenticação
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
