package com.raboBank.users.account.model;

import java.util.logging.Logger;

public class CreditCard implements Card {
    private static final Logger logger = Logger.getLogger(CreditCard.class.getName());

    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }
    @Override
    public void pay(double amount) {
        double totalAmount = amount + (amount * 0.01);
        logger.info("Paid " + totalAmount + " with credit card.");
    }
}
