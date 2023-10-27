package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class Deposit_Controller implements Initializable {

    @FXML
    private ChoiceBox<?> account_Selector;

    @FXML
    private TableColumn<?, ?> amount_col;

    @FXML
    private Hyperlink home_link;

    @FXML
    private TableColumn<?, ?> transactionName_col;

    @FXML
    private TableView<?> transaction_Table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_link.setOnAction(event -> DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null));
    }
}
