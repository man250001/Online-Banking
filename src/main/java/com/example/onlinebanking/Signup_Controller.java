package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Signup_Controller implements Initializable {

    @FXML
    private Button Signup_bttn;

    @FXML
    private Hyperlink login_link;

    @FXML
    private TextField password_tf, username_tf, lastname_tf, firstname_tf;

    @FXML
    private Label taken_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_link.setOnAction(event -> {
            DBUtils.changeScene(event, "login.fxml", "Login", null, null);
        });
        Signup_bttn.setOnAction(event -> {
            try {
                if (DBUtils.signUpUser(event, username_tf.getText(), password_tf.getText())) {
                    taken_label.setVisible(false);
                    DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null);
                }else{
                    taken_label.setVisible(true);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        });
    }
}
