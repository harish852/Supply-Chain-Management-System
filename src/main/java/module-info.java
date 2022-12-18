module com.example.supplychainhari17dec {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainhari17dec to javafx.fxml;
    exports com.example.supplychainhari17dec;
}