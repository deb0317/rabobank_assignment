package com.raboBank.users.account.repository;

import com.raboBank.users.account.model.User;
import com.raboBank.users.account.service.BankAccount;
import com.raboBank.users.account.service.CreditCard;
import com.raboBank.users.account.service.DebitCard;

import java.util.Arrays;
import java.util.List;

public class DummyData {
    public static List<User> users;

   static {
        // Create static user, card, and account data
        User user1 = new User("U1", "John Doe", "123 Main St");
        User user2 = new User("U2", "Jane Smith", "456 Elm St");

        DebitCard debitCard1 = new DebitCard("1234 5678 9012 3456");
        CreditCard creditCard1 = new CreditCard("9876 5432 1098 7654");

        BankAccount account1 = new BankAccount(1234567890, 1000.0, user1, debitCard1);
        BankAccount account2 = new BankAccount(1234567893, 2000.0, user1, creditCard1);
        BankAccount account3 = new BankAccount(1234567892, 1500.0, user2, debitCard1);
        BankAccount account4 = new BankAccount(1234567891, 2500.0, user2, creditCard1);

        user1.setAccounts(Arrays.asList(account1, account2));
        user2.setAccounts(Arrays.asList(account3, account4));

        users = Arrays.asList(user1, user2);
    }

}
