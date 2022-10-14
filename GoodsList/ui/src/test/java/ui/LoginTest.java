package ui;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * TestFX App test
 */
public class LoginTest extends ApplicationTest {
    
    private FxRobot robot = new FxRobot();
    private final String REGISTER_USERNAME_ID = "#registrationUsername";
    private final String REGISTER_FULLNAME_ID = "#fullName";
    private final String REGISTER_PASSWORD_ID = "#registrationPassword";
    private final String REGISTER_REPEATED_PASSWORD_ID = "#repeatedRegistrationPassword";
    private final String REGISTER_BUTTON_ID = "#registrationButton";
    private final String LOGIN_USERNAME_ID = "#username";
    private final String LOGIN_PASSWORD_ID = "#password";

    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
            final Parent root = fxmlLoader.load();
            //this.loginController = fxmlLoader.getController();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @Test
    @DisplayName("Test if a new user can be registered")
    public void testRegisterUser() throws IOException {
        String invalidUserName = "Freddy";
        String invalidFullName = "Kavring Kavringsen";
        String invalidPassword1 = "mittPassord1";
        String invalidPassword2 = "ikkeLikt9";
        robot.clickOn(REGISTER_USERNAME_ID).write(invalidUserName);
        robot.clickOn(REGISTER_FULLNAME_ID).write(invalidFullName);
        robot.clickOn(REGISTER_PASSWORD_ID).write(invalidPassword1);
        robot.clickOn(REGISTER_REPEATED_PASSWORD_ID).write(invalidPassword2);
        robot.clickOn(REGISTER_BUTTON_ID);
        sleep(200);
        robot.clickOn(850, 390);

        String validUserName = "emilklo";
        String validFullName = "Emil Klovning";
        String validPassword = "b935wzzEmil";
        robot.doubleClickOn(REGISTER_USERNAME_ID).eraseText(1).write(validUserName);
        robot.doubleClickOn(REGISTER_FULLNAME_ID).clickOn(REGISTER_FULLNAME_ID).eraseText(1).write(validFullName);
        robot.doubleClickOn(REGISTER_PASSWORD_ID).eraseText(1).write(validPassword);
        robot.doubleClickOn(REGISTER_REPEATED_PASSWORD_ID).eraseText(1).write(validPassword);
        robot.clickOn(REGISTER_BUTTON_ID);
        sleep(200);
        robot.clickOn(850, 390);

        String invalidLoginUserName = "fizzemannen";
        String invalidLoginPassword = "qwerty123";
        robot.clickOn(LOGIN_USERNAME_ID).write(invalidLoginUserName);
        robot.clickOn(LOGIN_PASSWORD_ID).write(invalidLoginPassword);
        robot.clickOn("#loginButton");
        sleep(200);
        robot.clickOn(850, 390);

        String takenLoginUserName = "emilklo";
        String takenLoginPassword = "b935wzzEmil";
        robot.doubleClickOn(LOGIN_USERNAME_ID).eraseText(1).write(takenLoginUserName);
        robot.doubleClickOn(LOGIN_PASSWORD_ID).eraseText(1).write(takenLoginPassword);
        robot.clickOn("#loginButton");
        sleep(200);
        sleep(1000);
    }
}
