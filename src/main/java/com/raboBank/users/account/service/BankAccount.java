package com.raboBank.users.account.service;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.model.Transaction;
import com.raboBank.users.account.model.User;

import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Account {
    private final int accountNumber;
    private double balance;
    private final Card card;
    private final List<Transaction> transactions;

    public BankAccount(int accountNumber, double balance, User user, Card card) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.card = card;
        this.transactions = new ArrayList<>();
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String withdraw(double amount,Account account) {
        amountValidation(amount);
        if (card instanceof CreditCard) {
            double extraCharge = amount * 0.01;
            amount += extraCharge;
        }
        balance -= amount;
        addTransaction(new Transaction("Withdrawal", amount));
        return String.format("Transfer Amount: %.2f to account number: %s", amount, account.getAccountNumber());
    }

    @Override
    public String transfer(double amount, Account destinationAccount) {
        amountValidation(amount);
        if (card instanceof CreditCard) {
            double extraCharge = amount * 0.01;
            amount += extraCharge;
        }
        balance -= amount;
        destinationAccount.deposit(amount);
        addTransaction(new Transaction("Transfer", amount));
        return String.format("Transfer Amount: %.2f to account number: %s", amount, destinationAccount.getAccountNumber());
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        addTransaction(new Transaction("Deposit", amount));
    }
    private void amountValidation(double amount){
        if (amount > balance) {
            throw new UserAccountException("Insufficient funds in the account");
        }
        if (amount <= 0) {
            throw new UserAccountException("Amount cant be zero or negative");
        }
    }
}
