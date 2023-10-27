package com.example.onlinebanking;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage_Controller implements Initializable {

    @FXML
    private Hyperlink checkingaccount_link, payBills_link, savingsaccount_link, signout_link, transfer_link, admin_link;

    @FXML
    private Label balance_label;

    @FXML
    private ChoiceBox<Account> account_cb;

    @FXML
    private TableView<String> transaction_tbl;

    @FXML
    private TableColumn<String, String> Amount_col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get the user's accounts from DBUtils
        List<Account> userAccounts = DBUtils.getUser().getAccounts();

        // Create an ObservableList of the user's accounts
        ObservableList<Account> accountList = FXCollections.observableArrayList(userAccounts);

        // Set the items property of the ChoiceBox to the ObservableList
        account_cb.setItems(accountList);

        balance_label.setText("Select an account to view balance");
        checkingaccount_link.setOnAction(event -> DBUtils.changeScene(event, "Deposit.fxml", "Checking Account", null, null));
        payBills_link.setOnAction(event -> DBUtils.changeScene(event, "payBills.fxml", "Pay Bills", null, null));
        savingsaccount_link.setOnAction(event -> DBUtils.changeScene(event, "Withdraw.fxml", "Savings Account", null, null));
        signout_link.setOnAction(event -> {
            DBUtils.getUser().signOutUser();
            DBUtils.changeScene(event, "login.fxml", "Login", null, null);
        });
        transfer_link.setOnAction(event -> DBUtils.changeScene(event, "transferFunds.fxml", "Transfer Funds", null, null));
        admin_link.setOnAction(event -> DBUtils.changeScene(event, "adminPage.fxml", "Admin", null, null));
        account_cb.setOnAction(event -> {
            // Get the selected account from the ChoiceBox
            Account selectedAccount = account_cb.getSelectionModel().getSelectedItem();

            // Set the balance label to the balance of the selected account
            balance_label.setText("$" + selectedAccount.getBalance());

            
            List<String> userTransactions = DBUtils.getUser().getTransactionsByAccountNum(selectedAccount.getAccountNumber());
            ObservableList<String> transactionList = FXCollections.observableArrayList(userTransactions);

            
            transaction_tbl.setItems(transactionList);
            Amount_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

            
            transaction_tbl.getColumns().setAll(Amount_col);
        });
    }
}
