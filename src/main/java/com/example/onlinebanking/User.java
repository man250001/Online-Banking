package com.example.onlinebanking;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class User {

        // note to Moron(Self) get accounts and make withdraw/deposit functionality work

        String firstname, lastname;
        int userid, total_balance;
        ArrayList<Account> accounts;
        ArrayList<Transaction> transactions;

        public User(String firstname, String lastname, int userid, int total_balance, ArrayList<Account> accounts) {
                this.firstname = firstname;
                this.lastname = lastname;
                this.userid = userid;
                this.total_balance = total_balance;
                this.accounts = accounts;
                transactions = new ArrayList<>();
        }

        // region Getters and Setters
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
                return total_balance;
        }

        public void setBalance(int balance) {
                this.total_balance = balance;
        }

        public ArrayList<Account> getAccounts() {
                return accounts;
        }

        public void setAccounts(ArrayList<Account> accounts) {
                this.accounts = accounts;
        }
        // endregion

        // Withdrawal and Deposit

        public ArrayList<String> getTransactionsByAccountNum(int accountId) {
                ArrayList<String> tList = new ArrayList<>();
                try {
                        for (Account a : accounts) {
                                if (a.getAccountNumber() == accountId) {
                                        Connection conn = DriverManager.getConnection(
                                                        "jdbc:mysql://localhost:3306/banking", "root", "password");
                                        PreparedStatement psGetTransactions = conn.prepareStatement(
                                                        "SELECT * FROM transactions WHERE accountId = ?");

                                        psGetTransactions.setInt(1, accountId);
                                        ResultSet transactions = psGetTransactions.executeQuery();
                                        while (transactions.next()) {
                                                tList.add(transactions.getString("amount")
                                                                + transactions.getString("descriptor"));

                                        }
                                }
                        }
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
                return tList;
        }

        // Recieve any transaction made by an account
        public void recieveTransaction(Transaction transaction) {
                transactions.add(transaction);
        }

        // When user signs out return all bank account data to the database
        public void signOutUser() {
                try {
                        Connection conn;
                        PreparedStatement psSetUserData;

                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "password");
                        psSetUserData = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE accountId = ?");
                        // store account data in database
                        for (Account a : accounts) {
                                psSetUserData.setInt(1, a.getBalance());
                                psSetUserData.setInt(2, a.getAccountNumber());
                                psSetUserData.executeUpdate();
                        }

                        psSetUserData = conn.prepareStatement(
                                        "INSERT INTO transactions (amount, descriptor, accountId) VALUES (?, ?, ?)");
                        // store transaction data in database
                        for (Transaction transaction : transactions) {
                                psSetUserData.setInt(1, transaction.getAmount());
                                psSetUserData.setString(2, transaction.getDescriptor());
                                psSetUserData.setInt(3, transaction.getAccountNumber());
                                psSetUserData.executeUpdate();
                        }

                        // reset all account data variables for the next user
                        total_balance = 0;
                        accounts = null;
                        firstname = null;
                        lastname = null;
                        userid = 0;
                        // close all connections
                        psSetUserData.close();
                        conn.close();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
}
