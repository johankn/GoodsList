package ui;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import core.LoginUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    private LoginUser user;
    
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button loginButton;
    
    @FXML 
    private Text header;

    @FXML
    private void onLogin(){
        user = new LoginUser(username.getText(),password.getText());
        //TODO: Write code that writes user to file and canges scene.
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
