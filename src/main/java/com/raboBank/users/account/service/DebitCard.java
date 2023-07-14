package com.raboBank.users.account.service;

public class DebitCard implements Card {
    private String cardNumber;

    public DebitCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }
}
