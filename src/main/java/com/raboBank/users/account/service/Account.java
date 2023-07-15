package com.raboBank.users.account.service;

import com.raboBank.users.account.model.Transaction;

import java.util.List;

/**
 * Represents a bank account.
 */
public interface Account {
    /**
     * Retrieves the account number.
     *
     * @return the account number
     */
    int getAccountNumber();

    /**
     * Retrieves the current balance of the account.
     *
     * @return the account balance
     */
    double getBalance();

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount   the amount to withdraw
     * @param account  the source account from where the withdrawal is made
     * @return a status message indicating the result of the withdrawal
     */
    String withdraw(double amount, Account account);

    /**
     * Transfers the specified amount from the account to the destination account.
     *
     * @param amount              the amount to transfer
     * @param destinationAccount  the destination account to transfer the amount
     * @return a status message indicating the result of the transfer
     */
    String transfer(double amount, Account destinationAccount);

    /**
     * Adds a transaction to the account.
     *
     * @param transaction  the transaction to add
     */
    void addTransaction(Transaction transaction);

    /**
     * Retrieves the list of transactions associated with the account.
     *
     * @return the list of transactions
     */
    List<Transaction> getTransactions();

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount  the amount to deposit
     */
    void deposit(double amount);
}
