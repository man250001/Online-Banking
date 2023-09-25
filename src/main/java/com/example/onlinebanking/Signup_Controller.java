package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Signup_Controller implements Initializable {

    @FXML
    private Button Signup_bttn;

    @FXML
    private Hyperlink login_link;

    @FXML
    private TextField password_tf;

    @FXML
    private TextField username_tf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_link.setOnAction(event -> {
            DBUtils.changeScene(event, "login.fxml", "Login", null, null);
        });
        Signup_bttn.setOnAction(event -> {
            //Sign Up with DB integration
        });
    }
}
