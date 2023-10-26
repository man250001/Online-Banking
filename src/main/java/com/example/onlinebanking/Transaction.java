package com.example.onlinebanking;

public class Transaction {

    int amount, accountNumber;
    String descriptor;

    public Transaction(int amount, String descriptor, int accountNumber) {
        this.amount = amount;
        this.descriptor = descriptor;
        this.accountNumber = accountNumber;
    }

    //region Getters and Setters
    public int getAmount() {
        return amount;
    }
    public String getDescriptor() {
        return descriptor;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    //endregion

}
