package ui;

import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import core.FileOperator;
import core.LoginValidator;
import core.RegisteredUser;
import core.RegistrationValidator;
import core.User;
import core.UserInfoCollector;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class LoginController {

    private FileOperator fileOperator;
    private RegistrationValidator registrationValidator;
    private LoginValidator loginValidator;
    private UserInfoCollector userInfoFinder;
    private User loggedInUser;
    private App app;
    private String filename;

    @FXML
    private PasswordField password, registrationPassword, repeatedRegistrationPassword;

    @FXML
    private TextField username, registrationUsername, fullName;

    @FXML
    private Button loginButton, egistrationButton;

    @FXML
    private Text header, loginHeader, registrationHeader, feedback;

    public void setFilepath(boolean isTest) {
        if (isTest) {
            this.filename = "..//ui/src/test/resources/ui/uiTest.json";
        }
        else {
            this.filename = "..//core/src/main/java/json/dataObjects.json";
        }
    }

    private void displayError(String message) {
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
    private void onLogin() {
        try {
            userInfoFinder = new UserInfoCollector();
            loginValidator = new LoginValidator();
            if (loginValidator.isLoginLegal(username.getText(), password.getText(), loginValidator.getFileOperator().getAllUsersAsList(filename))) {
                loggedInUser = new User(username.getText(), password.getText(),
                        userInfoFinder.getFullNameByUsername(filename, username.getText()), new ArrayList<>());
                app = new App();
                app.bringUserInfo(loggedInUser);
            }
        } catch (IOException | IllegalArgumentException e) {
            displayError(e.getMessage());
            this.username.clear();
            this.password.clear();
        }
    }

    

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
