package com.mfaias.fitnesspersonal.dto.request;

import java.util.UUID;

public record ItemProductRequestDTO(UUID produtoId,
                                    Integer quantidade) {
}
