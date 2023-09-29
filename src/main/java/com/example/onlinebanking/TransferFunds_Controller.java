package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class TransferFunds_Controller implements Initializable {

    @FXML
    private ChoiceBox<?> from_box;

    @FXML
    private Hyperlink home_link;

    @FXML
    private ChoiceBox<?> to_box;

    @FXML
    private Button transfer_bttn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_link.setOnAction(event -> DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null));
    }
}
