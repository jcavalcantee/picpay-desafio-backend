package com.jefferson.desafiopicpay.services;

import com.jefferson.desafiopicpay.domain.User;
import com.jefferson.desafiopicpay.domain.UserType;
import com.jefferson.desafiopicpay.dtos.UserDTO;
import com.jefferson.desafiopicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public User getUserById(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    public void validateUser(User sender, BigDecimal amount) throws Exception{

        if(sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não pode realizar transações");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw  new Exception("Saldo insuficiente");
        }
    }
}
