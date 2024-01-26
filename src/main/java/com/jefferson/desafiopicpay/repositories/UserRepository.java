package com.jefferson.desafiopicpay.repositories;

import com.jefferson.desafiopicpay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
