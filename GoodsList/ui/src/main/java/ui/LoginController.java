package ui;


import java.io.FileNotFoundException;

import core.FileOperator;
import core.LoginUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    private LoginUser user;
    private FileOperator fileOperator;
    
    @FXML
    private PasswordField password;
    private PasswordField registrationPassword;
    private PasswordField repeatedregistrationPassword;


    @FXML
    private TextField username;
    private TextField registrationUsername;
    private TextField fullName;

    @FXML
    private Button loginButton;
    private Button registrationButton;
    
    @FXML 
    private Text header;
    private Text loginHeader;
    private Text registrationHeader;

    @FXML
    private void onRegistration(){
        fileOperator = new FileOperator();
        String path = "GoodsList/core/src/main/java/json/dataObjects.json";
        user = new LoginUser("Per", "g");

        try {
            fileOperator.writeUserDataToFile(path, user);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogin(){
        user = new LoginUser(username.getText(),password.getText());
        //TODO: Write code that writes user to file and canges scene.
        try {
            fileOperator = new FileOperator();
            fileOperator.writeUserToFile("src/main/resources/ui/users.txt", username.getText()
            + ";" + password.getText());
            App main = new App();
            main.setHomePage("App.fxml");
            main.bringUserInfo(user.getUsername());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LoginController l = new LoginController();
        l.onRegistration();
    }
}
