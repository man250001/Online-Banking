module com.example.onlinebanking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.onlinebanking to javafx.fxml;
    exports com.example.onlinebanking;
}