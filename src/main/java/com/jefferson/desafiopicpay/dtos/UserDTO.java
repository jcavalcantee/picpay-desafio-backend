package com.jefferson.desafiopicpay.dtos;

import com.jefferson.desafiopicpay.domain.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String email,
        String password,
        String document,
        BigDecimal balance,
        UserType userType
) {

}
