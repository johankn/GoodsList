package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.Ad;
import json.Electronics;
import json.FileOperator;
import json.User;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import core.RegisteredUser;

/**
 * TestFX App test
 */
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UiTest extends ApplicationTest {

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

  /**
   * Start method for the app. We have added a method for setting the filepath we
   * are using in controller.
   * If the param for setFilePath is false, we are running the app normally, and
   * true means its a test.
   * 
   * @param stage
   * @throws IOException
   */
  @Override
  public void start(Stage stage) throws IOException {
    mainStage = stage;
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
    final Parent root = fxmlLoader.load();
    LoginController loginController = fxmlLoader.getController();
    loginController.setFilepath(true);
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Getter
   * @return Stage
   */
  public Stage getStage() {
    return mainStage;
  }

  /*
   * changes scene to the new homescene for given loginUser
   * 
   * @param fxml
   */
  private void setHomePage(User user) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
    Parent parent = fxmlLoader.load();
    AppController appController = fxmlLoader.getController();
    appController.setUsername(user);
    appController.setFilepath(true);
    appController.setChoiceBox();
    mainStage.getScene().setRoot(parent);
  }

  /*
   * closes an alert
   */
  private void closeAlert() {
    robot.clickOn("OK");
  }

  /*
   * makes an ad
   */
  private void makeAd() {
    robot.clickOn("#newAdButton");
  }

  /*
   * posts an ad
   */
  private void postAd() {
    robot.clickOn("#postButton");
  }

  /*
   * Makes user and registered user.
   */
  @BeforeEach
  public void setUp() {
    regUser = new RegisteredUser("test", "Test1234", "Test", "Test1234");
    invalidUser = new RegisteredUser("test", "Test1234", "Test", "test");
  }

  /*
   * clears all users from testfile
   */
  @AfterAll
  public void clearTestFile() {
    FileOperator fileOperator = new FileOperator();
    fileOperator.removeAllUsers("..//ui/src/test/resources/ui/uiTest.json");
  }

  @Test
  @DisplayName("Tests login whit a user that is not registrated")
  @Order(1)
  public void TestLoginWithoutRegistration() {
    robot.clickOn(loginButton);
    this.closeAlert();
    robot.clickOn(usernameField).write(regUser.getUsername(), 5);
    robot.clickOn(passwordField).write(regUser.getPassword(), 5);
    robot.clickOn(loginButton);
    this.closeAlert();
  }

  @Test
  @DisplayName("Test registration of a user.")
  @Order(2)
  public void TestRegistration() {
    robot.clickOn(registerButton);
    this.closeAlert();

    robot.clickOn(regUsername).write(invalidUser.getUsername(), 5);
    robot.clickOn(fullName).write(invalidUser.getFullName(), 5);
    robot.clickOn(regPassword).write(invalidUser.getPassword(), 5);
    robot.clickOn(repeatedRegPassword).write(invalidUser.getRepeatedPassword(), 5);
    robot.clickOn(registerButton);
    this.closeAlert();

    robot.clickOn(regUsername).write(regUser.getUsername(), 5);
    robot.clickOn(fullName).write(regUser.getFullName(), 5);
    robot.clickOn(regPassword).write(regUser.getPassword(), 5);
    robot.clickOn(repeatedRegPassword).write(regUser.getRepeatedPassword(), 5);
    robot.clickOn(registerButton);
    this.closeAlert();
  }

  /*
   * Method that logs in.
   */
  private void LogIn() {
    robot.clickOn(usernameField).write(regUser.getUsername(), 5);
    robot.clickOn(passwordField).write(regUser.getPassword(), 5);
    robot.clickOn(loginButton);
    FileOperator fileoperator = new FileOperator();
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    try {
      if (fileoperator.getAllUsersAsList("..//ui/src/test/resources/ui/uiTest.json").size() == 0) {
        this.setHomePage(
            new User(regUser.getUsername(), regUser.getPassword(), regUser.getFullName(), new ArrayList<>()));
      } else {
        for (User user : fileoperator.getAllUsersAsList("..//ui/src/test/resources/ui/uiTest.json")) {
          if (user.getUsername().equals(regUser.getUsername())) {
            this.setHomePage(user);
          }
        }
      }
      sleep(500);
    } catch (IOException e) {
      // TODO: handle exception
    }
  }

  @Test
  @DisplayName("Tests to make an electrinic ad")
  @Order(3)
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

    robot.clickOn("#titleField1").write(adTitle, 5);
    robot.clickOn("#descriptionArea1").write(adDescription, 5);
    robot.clickOn("#conditionField1");
    robot.clickOn("#priceField1").write(invalidPrice, 5);
    robot.clickOn("#brandField1").write(adBrand, 5);
    robot.clickOn("#typeField1").write(adType, 5);
    robot.clickOn("#makeAd1");
    this.closeAlert();

    robot.doubleClickOn("#priceField1").eraseText(invalidPrice.length()).write(validAdPrice, 5);
    robot.clickOn("#makeAd1");
    this.postAd();

    sleep(500);

  }

  @Test
  @Order(4)
  @DisplayName("Tests to make an clothing ad")
  public void TestMakeClothingAd() {
    this.LogIn();
    this.makeAd();
    robot.clickOn("#ClothesButton1");

    String adTitle = "Gucci pants";
    String adDescription = "Used gucci pants for sale";
    String adPrice = "5000";
    String adType = "Sweater";
    String validSize = "M";
    String adBrand = "Gucci";

    robot.clickOn("#titleField2").write(adTitle, 5);
    robot.clickOn("#descriptionArea2").write(adDescription, 5);
    robot.clickOn("#priceField2").write(adPrice, 5);
    robot.clickOn("#typeField2").write(adType, 5);
    robot.clickOn("#brandField2").write(adBrand, 5);
    robot.clickOn("#colourChoiceClothing");
    robot.clickOn("Black");
    robot.clickOn("#makeAd2");
    this.closeAlert();

    robot.clickOn("#sizeField2").write(validSize, 5);
    robot.clickOn("#makeAd2");
    this.postAd();

    sleep(500);
  }

  @Test
  @Order(5)
  @DisplayName("Tests to make an property ad")
  public void TestMakePropertyAd() {
    this.LogIn();
    this.makeAd();
    robot.clickOn("#PropertyButton1");

    String adTitle = "House for sale";
    String adDescription = "4 bedroom house in Oslo";
    String adPrice = "4250000";
    String validType = "House";
    String adYearBuilt = "2004";
    String adBedrooms = "4";
    String invalidadArea = "hundred";
    String validArea = "100";

    robot.clickOn("#titleField3").write(adTitle, 5);
    robot.clickOn("#descriptionArea3").write(adDescription, 5);
    robot.clickOn("#priceField3").write(adPrice, 5);
    robot.clickOn("#typeField3").write(validType, 5);
    robot.clickOn("#yearBuiltField3").write(adYearBuilt, 5);
    robot.clickOn("#bedroomsField3").write(adBedrooms, 5);
    robot.clickOn("#areaField3").write(invalidadArea, 5);
    robot.clickOn("#makeAd21");
    this.closeAlert();

    robot.doubleClickOn("#areaField3").eraseText(invalidadArea.length()).write(validArea, 5);
    robot.clickOn("#makeAd21");
    this.postAd();

    sleep(500);
  }

  @Test
  @Order(6)
  @DisplayName("Tests to make an vehicle ad")
  public void TestMakeVehicleAd() {
    this.LogIn();
    this.makeAd();
    robot.clickOn("#VehiclesButton1");

    String adTitle = "Volvo XC90";
    String adDescription = "new Volvo for sale";
    String adPrice = "900000";
    String adType = "XC 90";
    String adYear = "2022";
    String adBrand = "Volvo";

    robot.clickOn("#titleField4").write(adTitle, 5);
    robot.clickOn("#descriptionArea4").write(adDescription, 5);
    robot.clickOn("#priceField4").write(adPrice, 5);
    robot.clickOn("#conditionField4");
    robot.clickOn("#typeField4").write(adType, 5);
    robot.clickOn("#yearField4").write(adYear, 5);
    robot.clickOn("#brandField4").write(adBrand, 5);
    robot.clickOn("#makeAd22");
    this.closeAlert();

    robot.clickOn("#colourChoiceVehicles");
    robot.clickOn("White");
    robot.clickOn("#makeAd22");
    this.postAd();

    sleep(500);
  }

  @Test
  @Order(7)
  @DisplayName("Tests to make an book ad")
  public void TestMakeBookAd() {
    this.LogIn();
    this.makeAd();
    robot.clickOn("#BooksButton1");

    String adTitle = "George Orwell 1984";
    String adDescription = "The best-selling classic from George Orwell.";
    String adPrice = "149";
    String validPages = "328";
    String adAuthor = "George Orwell";
    String adGenre = "Sci-fi";
    String adYear = "1949";

    robot.clickOn("#titleField5").write(adTitle, 5);
    robot.clickOn("#descriptionArea5").write(adDescription, 5);
    robot.clickOn("#priceField5").write(adPrice, 5);
    robot.clickOn("#genreField5").write(adGenre, 5);
    robot.clickOn("#pagesField5").write(validPages, 5);
    robot.clickOn("#yearField5").write(adYear, 5);
    robot.clickOn("#makeAd23");
    this.closeAlert();

    robot.clickOn("#authorField5").write(adAuthor, 5);
    robot.clickOn("#makeAd23");
    this.postAd();

    sleep(500);
  }

}
