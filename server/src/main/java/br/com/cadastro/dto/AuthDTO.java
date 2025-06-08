package br.com.cadastro.dto;

import jakarta.validation.constraints.NotNull;

public record AuthDTO(
        @NotNull String email,
        @NotNull String password
) {
}
