package com.raboBank.users.account.service;

import com.raboBank.users.account.exception.UserAccountException;
import com.raboBank.users.account.model.Card;
import com.raboBank.users.account.model.CreditCard;
import com.raboBank.users.account.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for the AccountServiceImpl class.
 */
class AccountServiceImplTest {

    private AccountServiceImpl account;
    private Account destinationAccount;

    @BeforeEach
    public void setup() {
        String cardNumber = "9876 5432 1098 7654";
        Card card = new CreditCard(cardNumber);
        account = new AccountServiceImpl(123456, 1000.0, card);
        destinationAccount = new AccountServiceImpl(789012, 500.0, card);
    }

    @Test
    void testWithdrawSufficientFunds() {
        String expectedMessage = String.format("Transfer Amount: %.2f to account number: %s", 500.0, destinationAccount.getAccountNumber());
        String actualMessage = account.withdraw(500.0, destinationAccount);
        Assertions.assertEquals(expectedMessage, actualMessage);
        Assertions.assertEquals(500.0, account.getBalance());
        Assertions.assertEquals(500.0, destinationAccount.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        Assertions.assertThrows(UserAccountException.class, () -> {
            account.withdraw(1500.0, destinationAccount);
        });
        Assertions.assertEquals(1000.0, account.getBalance());
        Assertions.assertEquals(500.0, destinationAccount.getBalance());
    }

    @Test
    void testTransferSufficientFunds() {
        String expectedMessage = String.format("Transfer Amount: %.2f to account number: %s", 500.0, destinationAccount.getAccountNumber());
        String actualMessage = account.transfer(500.0, destinationAccount);
        Assertions.assertEquals(expectedMessage, actualMessage);
        Assertions.assertEquals(500.0, account.getBalance());
        Assertions.assertEquals(1000.0, destinationAccount.getBalance());
    }

    @Test
    void testTransferInsufficientFunds() {
        Assertions.assertThrows(UserAccountException.class, () -> {
            account.transfer(1500.0, destinationAccount);
        });
        Assertions.assertEquals(1000.0, account.getBalance());
        Assertions.assertEquals(500.0, destinationAccount.getBalance());
    }

    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction("Deposit", 100.0);
        account.addTransaction(transaction);
        List<Transaction> transactions = account.getTransactions();
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertEquals(transaction, transactions.get(0));
    }

    @Test
    void testDeposit() {
        account.deposit(200.0);
        Assertions.assertEquals(1200.0, account.getBalance());
    }
}
