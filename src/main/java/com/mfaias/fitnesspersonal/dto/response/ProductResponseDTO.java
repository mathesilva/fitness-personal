package com.mfaias.fitnesspersonal.dto.response;

import com.mfaias.fitnesspersonal.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String nome,BigDecimal valor, Integer quantidadeEstoque) {
    public ProductResponseDTO (Product product){
        this(product.getId(), product.getNome(), product.getValor(), product.getQuantidadeEstoque());
    }
}
