package com.jefferson.desafiopicpay.repositories;

import com.jefferson.desafiopicpay.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
