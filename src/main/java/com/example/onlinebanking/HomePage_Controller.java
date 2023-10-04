package com.example.onlinebanking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePage_Controller implements Initializable {

    @FXML
    private Hyperlink checkingaccount_link, payBills_link, savingsaccount_link, signout_link, transfer_link, admin_link;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkingaccount_link.setOnAction(event -> DBUtils.changeScene(event, "checkingAccount.fxml", "Checking Account", null, null));
        payBills_link.setOnAction(event -> DBUtils.changeScene(event, "payBills.fxml", "Pay Bills", null, null));
        savingsaccount_link.setOnAction(event -> DBUtils.changeScene(event, "savingsAccount.fxml", "Savings Account", null, null));
        signout_link.setOnAction(event -> DBUtils.changeScene(event, "login.fxml", "Login", null, null));
        transfer_link.setOnAction(event -> DBUtils.changeScene(event, "transferFunds.fxml", "Transfer Funds", null, null));
        admin_link.setOnAction(event -> DBUtils.changeScene(event, "admin.fxml", "Admin", null, null));
    }
}
