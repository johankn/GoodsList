package ui;


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
            fileOperator = new FileOperator();
            fileOperator.writeUserToFile("GoodsList/ui/src/main/resources/ui/users.txt", username.getText()
            + ";" + password.getText());
            App main = new App();
            main.setHomePage("App.fxml");
            main.bringUserInfo(user.getUsername());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
