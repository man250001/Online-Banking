package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SavingsAccount_Controller {

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

}
