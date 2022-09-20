package ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AppController {

    @FXML private Label WelcomeText;

    @FXML
    public void setUsername(String username){
        WelcomeText.setText(username);
    }

}
