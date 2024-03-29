package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {

    @FXML
    private Button Login_bttn;

    @FXML
    private TextField username_tf;

    @FXML
    private PasswordField password_pf;

    @FXML
    private Hyperlink signup_link;

    @FXML
    private Label incorrect_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Login_bttn.setOnAction(event -> {

            try {
                if (DBUtils.logInUser(event, username_tf.getText(), password_pf.getText())) {
                    incorrect_label.setVisible(false);
                    DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null);
                } else {
                    incorrect_label.setVisible(true);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        signup_link.setOnAction(event -> DBUtils.changeScene(event, "signup.fxml", "Sign Up", null, null));
    }
}
