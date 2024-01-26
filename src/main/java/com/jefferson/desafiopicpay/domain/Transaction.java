package com.jefferson.desafiopicpay.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name ="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime timeStamp;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, LocalDateTime timeStamp, User sender, User receiver) {
        this.id = id;
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver);
    }

    public int hashCode() {
        return Objects.hash(id, amount, timeStamp, sender, receiver);
    }
}
