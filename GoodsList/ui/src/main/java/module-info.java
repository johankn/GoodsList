module app.ui {
    requires transitive app.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens ui to javafx.graphics, javafx.fxml;
    
    opens dataaccess;
    exports dataaccess;
}
