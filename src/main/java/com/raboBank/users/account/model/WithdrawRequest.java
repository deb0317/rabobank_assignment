package com.raboBank.users.account.model;

public class WithdrawRequest {
   private final int accountNumber;
   String userName;
   double amount;

   public int getAccountNumber() {
      return accountNumber;
   }


   public String getUserName() {
      return userName;
   }

   public double getAmount() {
      return amount;
   }

   public void setAmount(double amount) {
      this.amount = amount;
   }

   public WithdrawRequest(int accountNumber, String userName, double amount) {
      this.accountNumber = accountNumber;
      this.userName = userName;
      this.amount = amount;

   }


}
