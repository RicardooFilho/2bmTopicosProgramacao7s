package br.com.cadastro.dto;

import br.com.cadastro.enums.Role;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull String name,
        @NotNull String email,
        @NotNull String password,
        @NotNull Role role
) {
}
