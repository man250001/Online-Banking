package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;

public class TransferFunds_Controller {

    @FXML
    private ChoiceBox<?> from_box;

    @FXML
    private Hyperlink home_Link;

    @FXML
    private ChoiceBox<?> to_box;

    @FXML
    private Button transfer_bttn;

}
