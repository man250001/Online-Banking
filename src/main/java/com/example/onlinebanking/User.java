package com.example.onlinebanking;

import java.sql.*;

@SuppressWarnings("unused")
public class User {

        String firstname, lastname;
        int userid, balance, accountNumber;

        public User(String firstname, String lastname, int userid, int balance, int accountNumber) {
                this.firstname = firstname;
                this.lastname = lastname;
                this.userid = userid;
                this.balance = balance;
                this.accountNumber = accountNumber;
        }

        //region Getters and Setters
        public String getFirstname() {
                return firstname;
        }
        public void setFirstname(String firstname) {
                this.firstname = firstname;
        }
        public String getLastname() {
                return lastname;
        }
        public void setLastname(String lastname) {
                this.lastname = lastname;
        }
        public int getUserid() {
                return userid;
        }
        public void setUserid(int userid) {
                this.userid = userid;
        }
        public int getBalance() {
                return balance;
        }
        public void setBalance(int balance) {
                this.balance = balance;
        }
        public int getAccountNumber() {
                return accountNumber;
        }
        public void setAccountNumber(int accountNumber) {
                this.accountNumber = accountNumber;
        }
        //endregion

        //Withdrawal and Deposit
        public void withdraw(int amount){
                balance -= amount;
        }
        public void deposit(int amount){
                balance += amount;
        }

        //When user signs out return all bank account data to the database
        public void signOutUser(){
                try {
                        Connection conn;
                        PreparedStatement psSetUserData;

                        //store account data in database

                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking", "root", "password");
                        psSetUserData = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE accountId = ?");
                        psSetUserData.setInt(1, balance);
                        psSetUserData.setInt(2, accountNumber);
                        psSetUserData.executeUpdate();
                        //reset all account data variables for the next user
                        balance = 0;
                        accountNumber = 0;
                        firstname = null;
                        lastname = null;
                        userid = 0;
                        //close all connections
                        psSetUserData.close();
                        conn.close();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
}
