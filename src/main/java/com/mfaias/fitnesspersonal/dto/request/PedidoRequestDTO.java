package com.mfaias.fitnesspersonal.dto.request;

import com.mfaias.fitnesspersonal.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(String nomeClient,
                               List<Item> produtos) {
    public record Item(UUID produtoId, Integer quantidade, LocalDateTime dataPedido) {}
}
