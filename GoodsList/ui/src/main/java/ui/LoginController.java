package ui;

import javafx.fxml.FXML;

import java.io.FileNotFoundException;

import core.FileOperator;
import core.RegisteredUser;
import core.RegistrationValidator;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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

    private void displayError(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void onLogin(){

    }

    @FXML
    private void onRegistration() {
        RegistrationValidator validator = new RegistrationValidator();
        try {
            if (validator.isRegistrationLegal(registrationUsername.getText(), registrationPassword.getText(), repeatedRegistrationPassword.getText(), fullName.getText())) {
                RegisteredUser regUser = new RegisteredUser(registrationUsername.getText(), registrationPassword.getText(), fullName.getText(), repeatedRegistrationPassword.getText());
                this.displayMessage("You have been succesfully registered!");
                validator.getFileOperator().writeUserDataToFile(validator.getFilename(), regUser);
                this.registrationUsername.clear();
                this.registrationPassword.clear();
                this.repeatedRegistrationPassword.clear();
                this.fullName.clear();
            }
        } 
        catch (IllegalArgumentException e) {
            this.displayError(e.getMessage());
        }
        catch (FileNotFoundException e) {
            this.displayError("Something went wrong, there is a problem with the filepath");
        }
    }

    public static void main(String[] args) {
        LoginController l = new LoginController();
        l.onRegistration();
    }
}
