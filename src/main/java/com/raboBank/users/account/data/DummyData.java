package com.raboBank.users.account.data;

import com.raboBank.users.account.model.BankUser;
import com.raboBank.users.account.service.AccountServiceImpl;
import com.raboBank.users.account.model.CreditCard;
import com.raboBank.users.account.model.DebitCard;

import java.util.Arrays;
import java.util.List;

public class DummyData {
     public static final List<BankUser> users;

   static {
        // Create static user, card, and account data
        BankUser user1 = new BankUser("U1", "John Doe", "123 Main St");
        BankUser user2 = new BankUser("U2", "Jane Smith", "456 Elm St");

        DebitCard debitCard1 = new DebitCard("1234 5678 9012 3456");
        CreditCard creditCard1 = new CreditCard("9876 5432 1098 7654");

        AccountServiceImpl account1 = new AccountServiceImpl(1234567890, 1000.0, debitCard1);
        AccountServiceImpl account2 = new AccountServiceImpl(1234567893, 2000.0, creditCard1);
        AccountServiceImpl account3 = new AccountServiceImpl(1234567892, 1500.0, debitCard1);
        AccountServiceImpl account4 = new AccountServiceImpl(1234567891, 2500.0, creditCard1);

        user1.setAccounts(Arrays.asList(account1, account2));
        user2.setAccounts(Arrays.asList(account3, account4));

        users = Arrays.asList(user1, user2);
    }
}
