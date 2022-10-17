package ui;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import core.LoginValidator;
import core.RegisteredUser;
import core.RegistrationValidator;
import core.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import json.FileOperator;
import json.UserInfoCollector;

public class LoginController {

    private RegistrationValidator registrationValidator;
    private LoginValidator loginValidator;
    private User loggedInUser;
    private App app;
    private String filename;
    private FileOperator fileOperator;

    @FXML
    private PasswordField password, registrationPassword, repeatedRegistrationPassword;

    @FXML
    private TextField username, registrationUsername, fullName;

    @FXML
    private Button loginButton, egistrationButton;

    @FXML
    private Text header, loginHeader, registrationHeader, feedback;


    /** 
     * Method for choosing the filepath and hence which file we are writing and reading from. 
     * If istest is true it is a test, vice versa. 
     * @param isTest
     */
    public void setFilepath(boolean isTest) {
        if (isTest) {
            this.filename = "..//ui/src/test/resources/ui/uiTest.json";
        }
        else {
            this.filename = "..//ui/src/main/resources/ui/dataObjects.json";
        }
    }


    /** 
     * private method for displaying an error with the given param message
     * It os used when logging in or registrating, if something is wrong in the input fields. 
     * It gets the message from the exceptions, which is thrown by the registrationvalidator or loginvalidator classes. 
     * @param message
     */
    private void displayError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /** 
     * private method for displaying information to user. 
     * is used when logging in succesfully
     * @param message
     */
    private void displayMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /* 
     * This is the method which is called when pressing the login button
     * It check if the input fields contains valid information and if the user is registered to the system via the loginvalidator. 
     * if so, it creates a new user object
     * then it calls the method from app, which switches fxml scenes and brings the userinfo. 
     * If the login is not succesfull, it displays an error message with the exception message
     */
    @FXML
    private void onLogin() {
        try {
            fileOperator = new FileOperator();
            loginValidator = new LoginValidator();
            if (loginValidator.isLoginLegal(username.getText(), password.getText(), loginValidator.getFileOperator().getAllUsersAsList(filename))) {

                for (int i = 0; i < fileOperator.getAllUsersAsList(filename).size(); i++) {
                    if (fileOperator.getAllUsersAsList(filename).get(i).getUsername().equals(username.getText())){
                        loggedInUser = fileOperator.getAllUsersAsList(filename).get(i);
                        app = new App();
                        app.bringUserInfo(loggedInUser);
                    }
                }

            }
        } catch (IOException | IllegalArgumentException e) {
            displayError(e.getMessage());
            this.username.clear();
            this.password.clear();
        }
    }

    
    /* 
     * This method is called on when hitting the register button. 
     * It validates the input fields via a registrationvalidator, and writes the new user to file if true.
     * If the registration is not seccesfull it displays an error message to the user. 
     */
    @FXML
    private void onRegistration() {
        registrationValidator = new RegistrationValidator();
        try {
            if (registrationValidator.isRegistrationLegal(registrationUsername.getText(),
                    registrationPassword.getText(), repeatedRegistrationPassword.getText(), fullName.getText(), registrationValidator.getFileOperator().getAllUsersAsList(filename))) {
                RegisteredUser regUser = new RegisteredUser(registrationUsername.getText(),
                        registrationPassword.getText(), fullName.getText(), repeatedRegistrationPassword.getText());
                this.displayMessage("You have been succesfully registered!");
                registrationValidator.getFileOperator().writeNewUserDataToFile(filename, regUser);
                this.registrationUsername.clear();
                this.registrationPassword.clear();
                this.repeatedRegistrationPassword.clear();
                this.fullName.clear();
            }
        } catch (IllegalArgumentException e) {
            this.displayError(e.getMessage());
            this.registrationUsername.clear();
            this.registrationPassword.clear();
            this.repeatedRegistrationPassword.clear();
            this.fullName.clear();
        }
    }

}
