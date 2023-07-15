package com.raboBank.users.account.model;

import java.util.logging.Logger;

public class DebitCard implements Card {
    private static final Logger logger = Logger.getLogger(CreditCard.class.getName());

    private String cardNumber;

    public DebitCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }
    @Override
    public void pay(double amount) {
        logger.info("Paid " + amount + " with debit card.");
    }
}
