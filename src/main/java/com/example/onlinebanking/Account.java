package com.example.onlinebanking;

@SuppressWarnings("unused")
public class Account {

    int balance, accountNumber;

    public Account(int balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;

    }

    // region Getters and Setters
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    // endregion

    public void withdraw(int amount) {
        balance -= amount;
        DBUtils.getUser().recieveTransaction(new Transaction(amount, "-", accountNumber));
    }

    public void deposit(int amount) {
        balance += amount;
        DBUtils.getUser().recieveTransaction(new Transaction(amount, "+", accountNumber));
    }
}
