package ui;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import core.Ad;
import core.Electronics;
import core.RegisteredUser;
import core.User;

/**
 * TestFX App test
 */
@TestMethodOrder(OrderAnnotation.class)
public class UiFirstTest extends ApplicationTest {
    
    private final String regUsername = "#registrationUsername";
    private final String fullName = "#fullName";
    private final String regPassword = "#registrationPassword";
    private final String repeatedRegPassword = "#repeatedRegistrationPassword";
    private final String registerButton = "#registrationButton";
    private final String loginButton = "#loginButton";
    private final String usernameField = "#username";
    private final String passwordField = "#password";
    
    private RegisteredUser regUser;
    private RegisteredUser invalidUser;
    private static Stage mainStage;
    private FxRobot robot = new FxRobot();
    private Ad validDlectronicsAd;


    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        final Parent root = fxmlLoader.load();
        LoginController li= fxmlLoader.getController();
        li.setFilepath(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Stage getStage() {
        return mainStage;
    }

    //method called on for switching between the login and app scene. It also passes on the logged in User object. 
    private void setHomePage(User user) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent parent = fxmlLoader.load();
        AppController appController = fxmlLoader.getController();
        appController.setUsername(user);
        appController.setChoiceBox();
        mainStage.getScene().setRoot(parent);
    }

    private void closeAlert() {
        robot.clickOn("OK");
    }

    private void makeAd() {
        robot.clickOn("#newAdButton");
    }

    @BeforeEach
    public void setUp() {
        regUser = new RegisteredUser("test", "Test1234", "Test", "Test1234");
        invalidUser = new RegisteredUser("test", "Test1234", "Test", "test");
        validDlectronicsAd = new Ad("MacBook Pro 2022", new Electronics(20000, null, "Apple", "Laptop"), null, "Brand new MacBook Pro 2022");

    }

    // @Test
    // @Order(1)
    // public void TestLoginWithoutRegistration() {
    //     robot.clickOn(loginButton);
    //     this.closeAlert();
    //     robot.clickOn(usernameField).write(regUser.getUsername());
    //     robot.clickOn(passwordField).write(regUser.getPassword());
    //     robot.clickOn(loginButton);
    //     this.closeAlert();
    // }

    // @Test
    // @Order(2)
    // public void TestRegistration() {
    //     robot.clickOn(registerButton);
    //     this.closeAlert();

    //     robot.clickOn(regUsername).write(invalidUser.getUsername());
    //     robot.clickOn(fullName).write(invalidUser.getFullName());
    //     robot.clickOn(regPassword).write(invalidUser.getPassword());
    //     robot.clickOn(repeatedRegPassword).write(invalidUser.getRepeatedPassword());
    //     robot.clickOn(registerButton);
    //     this.closeAlert();

    //     robot.clickOn(regUsername).write(regUser.getUsername());
    //     robot.clickOn(fullName).write(regUser.getFullName());
    //     robot.clickOn(regPassword).write(regUser.getPassword());
    //     robot.clickOn(repeatedRegPassword).write(regUser.getRepeatedPassword());
    //     robot.clickOn(registerButton);
    //     this.closeAlert();
    // }

    // @Test
    // @Order(3)
    // public void TestLogInWithRegistration() {
    //     robot.clickOn(usernameField).write(regUser.getUsername());
    //     robot.clickOn(passwordField).write(regUser.getPassword());
    //     robot.clickOn(loginButton);
    //     try {
    //         this.setHomePage(new User(regUser.getUsername(), regUser.getPassword(), regUser.getFullName(), new ArrayList<>()));
    //         sleep(500);
    //     } catch (IOException e) {
    //         // TODO: handle exception
    //     }
    // }

    private void LogIn() {
        robot.clickOn(usernameField).write(regUser.getUsername());
        robot.clickOn(passwordField).write(regUser.getPassword());
        robot.clickOn(loginButton);
        try {
            this.setHomePage(new User(regUser.getUsername(), regUser.getPassword(), regUser.getFullName(), new ArrayList<>()));
            sleep(500);
        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    @Test
    @Order(4)
    public void TestMakeElectronicsAd() {
        this.LogIn();
        this.makeAd();
        robot.clickOn("#ElectronicsButton1");

        String adTitle = "MacBook Pro 2022";
        String adDescription = "Brand new MacBook Pro 2022";
        String invalidPrice = "twenty thousand";
        String validAdPrice = "20000";
        String adBrand = "Apple";
        String adType = "Laptop";

        robot.clickOn("#titleField1").write(adTitle);
        robot.clickOn("#descriptionArea1").write(adDescription);
        robot.clickOn("#conditionField1");
        robot.clickOn("#priceField1").write(invalidPrice);
        robot.clickOn("#brandField1").write(adBrand);
        robot.clickOn("#typeField1").write(adType);
        robot.clickOn("#makeAd1");
        this.closeAlert();


        robot.doubleClickOn("#priceField1").eraseText(invalidPrice.length()).write(validAdPrice);
        robot.clickOn("#makeAd1");
        this.makeAd();

        sleep(2000);

    }
    
}
