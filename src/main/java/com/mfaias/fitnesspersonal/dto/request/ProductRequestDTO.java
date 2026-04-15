package com.mfaias.fitnesspersonal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank(message = "O nome do produto é obrigatório")
                                String nome,
                                @NotNull(message = "o valor do produto é obrigátorio")
                                @Positive(message = "O valor deve ser maior que zero")
                                BigDecimal valor,
                                @NotNull(message = "a quantidade de estoque disponivel é obrigatória")
                                @PositiveOrZero(message = "O estoque não pode ser negativo")
                                Integer quantidadeEstoque) {
}
