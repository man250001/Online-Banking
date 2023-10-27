package com.example.onlinebanking;

import java.util.ArrayList;

public class Account {

    int balance, accountNumber;
    ArrayList<Transaction> transactions;

    public Account(int balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
    }

    //region Getters and Setters
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    //endregion

    public void withdraw(int amount){
        balance -= amount;
        transactions.add(new Transaction(amount, "-", accountNumber));
    }
    public void deposit(int amount){
        balance += amount;
        transactions.add(new Transaction(amount, "+", accountNumber));
    }
}
