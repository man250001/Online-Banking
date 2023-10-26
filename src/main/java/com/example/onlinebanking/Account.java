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

    public void withdraw(int amount){
        balance -= amount;
        transactions.add(new Transaction(amount, "-", accountNumber));
    }
    public void deposit(int amount){
        balance += amount;
        transactions.add(new Transaction(amount, "+", accountNumber));
    }
}
