module app.ui {
    requires app.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens ui to javafx.graphics, javafx.fxml;
}
