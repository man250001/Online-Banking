package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class adminController implements Initializable {

    @FXML
    private Button deposit_btn, withdraw_btn;

    @FXML
    private TextField withdraw_tf, deposit_tf;

    @FXML
    private Hyperlink return_link;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
return_link.setOnAction(event -> DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null));
        deposit_btn.setOnAction(event -> {
            try {
                DBUtils.getUser().deposit(Integer.parseInt(deposit_tf.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        withdraw_btn.setOnAction(event -> {
            try {
                DBUtils.getUser().withdraw(Integer.parseInt(withdraw_tf.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
