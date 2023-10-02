package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class Signup_Controller implements Initializable {

    @FXML
    private Button Signup_bttn;

    @FXML
    private Hyperlink login_link;

    @FXML
    private TextField username_tf, lastname_tf, firstname_tf;

    @FXML
    private PasswordField password_pf;

    @FXML
    private Label taken_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_link.setOnAction(event -> DBUtils.changeScene(event, "login.fxml", "Login", null, null));
        Signup_bttn.setOnAction(event -> {
            try {
                if (DBUtils.signUpUser(event, username_tf.getText(), password_pf.getText(), firstname_tf.getText(), lastname_tf.getText())) {
                    taken_label.setVisible(false);
                    DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null);
                }else{
                    taken_label.setVisible(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
