package com.example.onlinebanking;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

@SuppressWarnings({"unused", "DataFlowIssue"})
public class DBUtils {

    public static void changeScene(ActionEvent event, String _fxmlFile, String _title, String _username, String _password)
    {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(_fxmlFile));
            Parent root = loader.load();


            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(_title);
            stage.setResizable(false);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean signUpUser(ActionEvent event, String username, String pwd) throws SQLException {
        Connection conn;
        PreparedStatement psCheckIfUserExists;
        PreparedStatement psInsertUser = null;
        ResultSet resultSet;

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking", "root", "password");
        psCheckIfUserExists = conn.prepareStatement("Select * from user_logins Where username = ?");
        psCheckIfUserExists.setString(1, username);
        resultSet = psCheckIfUserExists.executeQuery();

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

        psInsertUser = conn.prepareStatement("insert into user_logins (username, pwd) values (?, ?)");
        psInsertUser.setString(1, username);
        psInsertUser.setString(2, pwd);
        psInsertUser.executeUpdate();


        resultSet.close();
        psInsertUser.close();
        psCheckIfUserExists.close();
        conn.close();
        return true;
    }

    public static boolean logInUser(ActionEvent event, String username, String pwd) throws SQLException {
        try {
            Connection conn;
            PreparedStatement psCheckIfUserExists;
            ResultSet resultSet;

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/banking", "root", "password");
            psCheckIfUserExists = conn.prepareStatement("SELECT * FROM user_logins WHERE username = ?");
            psCheckIfUserExists.setString(1, username);
            resultSet = psCheckIfUserExists.executeQuery();


            while (resultSet.next()) {
                String retrievedPassword = resultSet.getString("pwd");

                if (retrievedPassword.equals(pwd)) {
                    resultSet.close();
                    psCheckIfUserExists.close();
                    conn.close();
                    return true;
                }
            }

            System.out.println("Passwords did not match!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password is incorrect!");
            alert.show();
            resultSet.close();
            psCheckIfUserExists.close();
            conn.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
