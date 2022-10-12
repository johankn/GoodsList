package ui;

import javafx.fxml.FXML;
import core.FileOperator;
import core.RegisteredUser;
import core.RegistrationValidator;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    private FileOperator fileOperator;
    
    @FXML
    private PasswordField password, registrationPassword, repeatedRegistrationPassword;


    @FXML
    private TextField username, registrationUsername, fullName;

    @FXML
    private Button loginButton, egistrationButton;
    
    @FXML 
    private Text header, loginHeader, registrationHeader, feedback;

    @FXML
    private void onLogin(){

    }

    @FXML
    private void onRegistration() {
        RegistrationValidator validator = new RegistrationValidator();
        try {
            if (validator.isRegistrationLegal(registrationUsername.getText(), registrationPassword.getText(), repeatedRegistrationPassword.getText(), fullName.getText())) {
                RegisteredUser regUser = new RegisteredUser(registrationUsername.getText(), registrationPassword.getText(), fullName.getText(), repeatedRegistrationPassword.getText());
                feedback.setText("You have been succesfully registered!");
            }
        } 
        catch (IllegalArgumentException e) {
            feedback.setText(e.getMessage());
        }
    }
}
