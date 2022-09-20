package ui;

import core.Calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AppController {

    @FXML private Label WelcomeText;

    @FXML
    private void setUsername(String username){
        WelcomeText.setText(username);
    }

}
