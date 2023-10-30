package com.example.onlinebanking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class Deposit_Controller implements Initializable {

    @FXML
    private ChoiceBox<Account> account_Selector;

    @FXML
    private TextField amount_field;

    @FXML
    private Hyperlink home_link;

    @FXML
    private Button deposit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Account> userAccounts = DBUtils.getUser().getAccounts();
        ObservableList<Account> accountList = FXCollections.observableArrayList(userAccounts);
        account_Selector.setItems(accountList);

        home_link.setOnAction(event -> DBUtils.changeScene(event, "homePage.fxml", "Home Page", null, null));
        amount_field.setOnAction(event -> deposit_btn.setDisable(amount_field.getText().isEmpty() || account_Selector.getValue() == null));
        account_Selector.setOnAction(event -> deposit_btn.setDisable(amount_field.getText().isEmpty() || account_Selector.getValue() == null));
        deposit_btn.setOnAction(event -> {
            try {
                account_Selector.getValue().deposit(Integer.parseInt(amount_field.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
