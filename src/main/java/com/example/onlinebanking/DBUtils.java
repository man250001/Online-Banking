package com.example.onlinebanking;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {

    public static void changeScene(ActionEvent event, String _fxmlFile, String _title, String _username, String _password)
    {
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(_fxmlFile));
            Parent root = loader.load();


            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle(_title);
            stage.setResizable(false);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
