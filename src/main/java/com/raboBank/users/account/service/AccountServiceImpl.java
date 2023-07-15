package com.raboBank.users.account.service;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.model.Card;
import com.raboBank.users.account.model.CreditCard;
import com.raboBank.users.account.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Account interface representing a bank account.
 */
public class AccountServiceImpl implements Account {

    private final int accountNumber;
    private double balance;
    private final Card card;
    private final List<Transaction> transactions;

    /**
     * Constructs a new AccountServiceImpl with the specified account number, balance, and card.
     *
     * @param accountNumber the account number
     * @param balance       the initial account balance
     * @param card          the card associated with the account
     */
    public AccountServiceImpl(int accountNumber, double balance, Card card) {
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
    public String withdraw(double amount, Account account) {
        amountValidation(amount);
        if (card instanceof CreditCard) {
            card.pay(amount);
        }
        balance -= amount;
        addTransaction(new Transaction("Withdrawal", amount));
        return String.format("Transfer Amount: %.2f to account number: %s", amount, account.getAccountNumber());
    }

    @Override
    public String transfer(double amount, Account destinationAccount) {
        amountValidation(amount);
        if (card instanceof CreditCard) {
            card.pay(amount);
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

    /**
     * Performs amount validation for withdrawals and transfers.
     *
     * @param amount the amount to validate
     * @throws UserAccountException if the amount is greater than the balance or is zero/negative
     */
    private void amountValidation(double amount) {
        if (amount > balance) {
            throw new UserAccountException("Insufficient funds in the account");
        }
        if (amount <= 0) {
            throw new UserAccountException("Amount cannot be zero or negative");
        }
    }
}
