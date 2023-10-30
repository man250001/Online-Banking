package com.example.onlinebanking;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings({ "unused", "DataFlowIssue" })
public class DBUtils {

    static User currentUser;
    public static int tempBalance = 1000;

    // This method is used to change the scene when the user clicks on a
    // hyperlink/button
    public static void changeScene(ActionEvent event, String _fxmlFile, String _title, String _username,
            String _password) {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(_fxmlFile));
            Parent root = loader.load();

            Platform.runLater(() -> {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(_title);
                stage.setResizable(false);
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            });
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    // This method is used to add a new user to the database when they sign up
    public static boolean signUpUser(ActionEvent event, String username, String pwd, String firstname, String lastname)
            throws SQLException {
        Connection conn;
        PreparedStatement psCheckIfUserExists;
        PreparedStatement psInsertUser = null;
        PreparedStatement psInsertAccount;
        PreparedStatement psInsertUserLogin;
        ResultSet resultSet;

        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "password");
        psCheckIfUserExists = conn.prepareStatement("Select * from user_logins Where username = ?");
        psCheckIfUserExists.setString(1, username);
        resultSet = psCheckIfUserExists.executeQuery();

        // Check if the user to be added already exists
        while (resultSet.next()) {
            System.out.println("test");
            if (resultSet.getString("username").equals(username)) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user already exists!");
                alert.show();
                resultSet.close();
                psInsertUser.close();
                psCheckIfUserExists.close();
                conn.close();
                return false;
            }
        }

        // Add a new user to the users table
        psInsertUser = conn.prepareStatement("insert into users (f_name, l_name) values (?, ?)");
        psInsertUser.setString(1, firstname);
        psInsertUser.setString(2, lastname);
        psInsertUser.executeUpdate();

        // Get the user id of the user that was just created
        PreparedStatement psGetUserId = conn.prepareStatement(
                "select userId from users where f_name = ? and l_name = ?", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        psGetUserId.setString(1, firstname);
        psGetUserId.setString(2, lastname);

        ResultSet newUser = psGetUserId.executeQuery();
        newUser.afterLast();
        newUser.previous();
        int newUserId = newUser.getInt("userId");

        // Add a new user login for the user that was just created
        psInsertUserLogin = conn.prepareStatement("insert into user_logins (userId ,username, pwd) values (?, ?, ?)");
        psInsertUserLogin.setInt(1, newUserId);
        psInsertUserLogin.setString(2, username);
        psInsertUserLogin.setString(3, pwd);
        psInsertUserLogin.executeUpdate();

        psInsertAccount = conn.prepareStatement("insert into accounts (userId, balance) values (?, ?)");
        psInsertAccount.setInt(1, newUserId);
        psInsertAccount.setInt(2, tempBalance);
        psInsertAccount.executeUpdate();

        // Get the account id of the new account that was just created
        PreparedStatement psGetAccountId = conn.prepareStatement("select accountId from accounts where userId = ?");
        psGetAccountId.setInt(1, newUserId);
        ResultSet newAccount = psGetAccountId.executeQuery();
        newAccount.next();
        ArrayList<Account> newAccountId = new ArrayList<>();
        newAccountId.add(new Account(tempBalance, newAccount.getInt("accountId")));

        // Close all connections
        newAccount.close();
        psGetAccountId.close();
        newUser.close();
        psGetUserId.close();
        resultSet.close();
        psInsertUser.close();
        psInsertAccount.close();
        psInsertUserLogin.close();
        psCheckIfUserExists.close();
        conn.close();
        // Create a new user object for the new user
        currentUser = new User(firstname, lastname, newUserId, tempBalance, newAccountId);
        return true;
    }

    // This method is used to log in a user when supplying a correct username and
    // password
    public static boolean logInUser(ActionEvent event, String username, String pwd) throws Exception {
        try {
            Connection conn;
            PreparedStatement psCheckIfUserExists;
            ResultSet resultSet;

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "password");
            psCheckIfUserExists = conn.prepareStatement("SELECT * FROM user_logins WHERE username = ?");
            psCheckIfUserExists.setString(1, username);
            resultSet = psCheckIfUserExists.executeQuery();

            while (resultSet.next()) {
                String retrievedPassword = resultSet.getString("pwd");

                if (retrievedPassword.equals(pwd)) {
                    // Get f_name and l_name and userid from users using the username of the user
                    // that is logging in
                    PreparedStatement psGetUserDetails = conn.prepareStatement("SELECT * FROM users WHERE userId = ?");
                    PreparedStatement psGetUserAccount = conn
                            .prepareStatement("SELECT * FROM accounts WHERE userId = ?");
                    psGetUserDetails.setInt(1, resultSet.getInt("userId"));
                    psGetUserAccount.setInt(1, resultSet.getInt("userId"));
                    ResultSet userDetails = psGetUserDetails.executeQuery();
                    ResultSet userAccount = psGetUserAccount.executeQuery();
                    userDetails.next();
                    String firstname = userDetails.getString("f_name");
                    String lastname = userDetails.getString("l_name");
                    int userid = userDetails.getInt("userId");
                    int total_balance = 0;
                    ArrayList<Account> accountNumbers = new ArrayList<>();
                    while (userAccount.next()) {
                        accountNumbers.add(new Account(userAccount.getInt("balance"), userAccount.getInt("accountId")));
                        total_balance += userAccount.getInt("balance");
                    }
                    // Create a new user object for the user that is logging in
                    currentUser = new User(firstname, lastname, userid, total_balance, accountNumbers);
                    // Closes all connections
                    userAccount.close();
                    psGetUserAccount.close();
                    userDetails.close();
                    psGetUserDetails.close();
                    resultSet.close();
                    psCheckIfUserExists.close();
                    conn.close();
                    return true;
                }
            }

            // Closes all connections and informs user that the password is incorrect
            System.out.println("Passwords did not match!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password is incorrect!");
            alert.show();
            resultSet.close();
            psCheckIfUserExists.close();
            conn.close();
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // region get currentUser
    public static User getUser() {
        return currentUser;
    }
    // endregion
}
