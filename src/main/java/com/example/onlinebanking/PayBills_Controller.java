package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PayBills_Controller implements Initializable {

    @FXML
    private Label AmountDue_label;

    @FXML
    private Hyperlink home_link;

    @FXML
    private ChoiceBox<?> account_menu;

    @FXML
    private Button pay_bttn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_link.setOnAction(event -> {
            DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null);
        });
    }
}
