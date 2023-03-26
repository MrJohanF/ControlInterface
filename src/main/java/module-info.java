module com.example.controlinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;


    opens com.example.controlinterface to javafx.fxml;
    exports com.example.controlinterface;
}