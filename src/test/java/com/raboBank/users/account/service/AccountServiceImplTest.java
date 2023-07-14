package com.raboBank.users.account.service;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.service.Account;
import com.raboBank.users.account.model.Card;
import com.raboBank.users.account.model.CreditCard;
import com.raboBank.users.account.model.Transaction;
import com.raboBank.users.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AccountServiceImplTest {
    private Account account;
    private Account destinationAccount;

    @BeforeEach
    public void setup() {
        // Create a dummy account with initial balance and card
        Card card = new CreditCard("1234567890");
        account = new AccountServiceImpl(1, 1000.0, card);
        destinationAccount = new AccountServiceImpl(2, 2000.0, card);
    }

    @Test
    public void testWithdrawSuccessful() {
        double amount = 500.0;
        String expectedMessage = "Transfer Amount: 500.00 to account number: 2";

        String actualMessage = account.withdraw(amount, destinationAccount);

        Assertions.assertEquals(495.0, account.getBalance());
        List<Transaction> transactions = account.getTransactions();
        Assertions.assertEquals(1, transactions.size());
        Transaction transaction = transactions.get(0);
        Assertions.assertEquals("Withdrawal", transaction.getType());
        Assertions.assertEquals(505.0, transaction.getAmount());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        double amount = 1500.0;

        Assertions.assertThrows(UserAccountException.class, () -> {
            account.withdraw(amount, destinationAccount);
        });
    }

    @Test
    public void testWithdrawNegativeAmount() {
        double amount = -200.0;

        Assertions.assertThrows(UserAccountException.class, () -> {
            account.withdraw(amount, destinationAccount);
        });
    }

    @Test
    public void testTransferSuccessful() {
        double amount = 500.0;
        String expectedMessage = "Transfer Amount: 500.00 to account number: 2";

        String actualMessage = account.transfer(amount, destinationAccount);

        Assertions.assertEquals(495.0, account.getBalance());
        Assertions.assertEquals(2505.0, destinationAccount.getBalance());

        List<Transaction> sourceTransactions = account.getTransactions();
        Assertions.assertEquals(1, sourceTransactions.size());
    }
}

