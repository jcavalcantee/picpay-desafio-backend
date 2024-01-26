package com.jefferson.desafiopicpay.domain;

import com.jefferson.desafiopicpay.dtos.UserDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String document;

    private BigDecimal balance;

    private UserType userType;

    public User() {

    }

    public User(Long id, String name, String email, String password, String document, BigDecimal balance, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.document = document;
        this.balance = balance;
        this.userType = userType;
    }

    public User(UserDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.document = dto.document();
        this.balance = dto.balance();
        this.userType = dto.userType();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(document, user.document) && Objects.equals(balance, user.balance) && userType == user.userType;
    }

    public int hashCode() {
        return Objects.hash(id, name, email, password, document, balance, userType);
    }
}
