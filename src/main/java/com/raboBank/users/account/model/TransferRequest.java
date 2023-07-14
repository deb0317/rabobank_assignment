package com.raboBank.users.account.model;

public class TransferRequest

{
    String userName;
    private final Integer sourceAccountNumber;
    private final int destinationAccountNumber;
    double amount;

    public TransferRequest(String userName, int sourceAccountNumber, int destinationAccountNumber, double amount) {
        this.userName = userName;
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }



    public int getDestinationAccountNumber() {
        return destinationAccountNumber;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
