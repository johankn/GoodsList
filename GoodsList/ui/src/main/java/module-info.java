module app.ui {
    exports dataaccess;
    exports ui;
    requires transitive app.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens ui to javafx.graphics, javafx.fxml;
    opens dataaccess;
}
