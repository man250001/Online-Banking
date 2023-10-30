module com.example.onlinebanking {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.sql;
    


    opens com.example.onlinebanking to javafx.fxml;
    exports com.example.onlinebanking;
}