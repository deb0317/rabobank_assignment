package com.raboBank.users.account.service;

import com.raboBank.users.account.model.Transaction;
import java.util.List;

public interface Account {
    int getAccountNumber();
    double getBalance();
    String withdraw(double amount,Account account);
    String transfer(double amount, Account destinationAccount);
    void addTransaction(Transaction transaction);
     List<Transaction> getTransactions();
    void deposit(double amount);

}
