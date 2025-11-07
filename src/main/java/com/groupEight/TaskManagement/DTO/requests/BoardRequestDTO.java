package com.groupEight.TaskManagement.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO de requisição para criação/atualização de uma Board.
 */
public record BoardRequestDTO(

        @NotNull(message = "O nome da board não pode ser nulo.")
        @NotBlank(message = "O nome da board não pode estar em branco.")
        String nome,

        @NotNull(message = "A equipe é obrigatória no momento da criação.")
        Long equipeId, // ⚠️ Este campo faz referência à entidade Equipe — será ajustado posteriormente.

        List<Long> tarefasIds // ⚠️ Referência à lista de Tarefas — também será ajustado posteriormente.
) {}
