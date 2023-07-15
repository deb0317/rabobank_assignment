package com.raboBank.users.account.model;

public class DebitCard implements Card {
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
        System.out.println("Paid " + amount + " with debit card.");
    }
}
