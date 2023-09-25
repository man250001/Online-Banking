package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {

    @FXML
    private Button Login_bttn;

    @FXML
    private TextField password_tf, username_tf;

    @FXML
    private Hyperlink signup_link;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Login_bttn.setOnAction(event -> {
            //Get DB info
            //Temporary Login
            DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null);
        });
        signup_link.setOnAction(event -> {
            DBUtils.changeScene(event, "signup.fxml", "Sign Up", null, null);
        });
    }
}
