package com.jefferson.desafiopicpay.services;

import com.jefferson.desafiopicpay.domain.Transaction;
import com.jefferson.desafiopicpay.domain.User;
import com.jefferson.desafiopicpay.dtos.TransactionDTO;
import com.jefferson.desafiopicpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userService.getUserById(transactionDTO.senderId());
        User receiver = this.userService.getUserById(transactionDTO.receiverId());

        userService.validateUser(sender, transactionDTO.amount());

        boolean isAuthorize = authorizedTransaction();

        if(!isAuthorize) {
            throw new Exception("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.amount()));

        notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso!");

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTransaction;
    }

    public boolean authorizedTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return  "Autorizado".equalsIgnoreCase(message);
        } else return false;
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
