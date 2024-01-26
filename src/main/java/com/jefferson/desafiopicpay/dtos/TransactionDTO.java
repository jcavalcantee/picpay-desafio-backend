package com.jefferson.desafiopicpay.dtos;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionDTO(
        Long senderId,
        Long receiverId,
        BigDecimal amount
) {
}
