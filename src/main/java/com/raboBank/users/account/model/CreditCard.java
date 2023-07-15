package com.raboBank.users.account.model;
public class CreditCard implements Card {
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
        System.out.println("Paid " + totalAmount + " with credit card.");
    }
}
