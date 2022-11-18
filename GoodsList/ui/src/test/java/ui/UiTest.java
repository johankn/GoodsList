package ui;

import core.RegisteredUser;
import dataaccess.LocalGoodsListAccess;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.FileOperator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

/** TestFX App test. */
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
  private RegisteredUser user1;
  private RegisteredUser user2;
  private FxRobot robot = new FxRobot();
  private AbstractController abstractController;

  /**
   * Start method for the app. We have added a method for setting the filepath we are using in
   * controller. If the param for setFilePath is false, we are running the app normally, and true
   * means its a test.
   *
   * @param stage stage
   * @throws IOException ioException
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    LoginController li = new LoginController();
    fxmlLoader.setController(li);
    fxmlLoader.setLocation(App.class.getResource("Login.fxml"));
    Parent parent = fxmlLoader.load();
    li.setFilepath(true);
    li.setDataAccess(new LocalGoodsListAccess(li.getFilename()));
    stage.setScene(new Scene(parent));
    stage.show();
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
    sleep(1000);
    robot.clickOn("#postButton");
  }

  /**
   * Makes user and registered user.
   */
  @BeforeEach
  public void setUp() {
    regUser = new RegisteredUser("test", "Test1234", "Test", "Test1234");
    invalidUser = new RegisteredUser("test", "Test1234", "Test", "test");
    user1 = new RegisteredUser("example1", "Test1234", "Example One", "Test1234");
    user2 = new RegisteredUser("example2", "Test1234", "Example Two", "Test1234");
  }

  /**
   * clears all users from testfile.
   */
  @AfterAll
  public void clearTestFile() {
    FileOperator fileOperator = new FileOperator();
    fileOperator.removeAllDataFromFile("..//ui/src/test/resources/ui/uiTest.json");
  }

  @Test
  @DisplayName("Tests login whit a user that is not registrated")
  @Order(1)
  public void testLoginWithoutRegistration() {
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
  public void testRegistration() {
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
  @Test
  @Order(3)
  public void logInAndMakeAds() {
    robot.clickOn(usernameField).write(regUser.getUsername(), 5);
    robot.clickOn(passwordField).write(regUser.getPassword(), 5);
    robot.clickOn(loginButton);
    sleep(1000);
    this.testMakeElectronicsAd();
    this.testMakeClothingAd();
    this.testMakePropertyAd();
    this.testMakeVehicleAd();
    this.testMakeBookAd();
    this.testYourProfile();
    this.logOut();
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void testMakeElectronicsAd() {
    this.makeAd();
    robot.clickOn("#electronicsButton");

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
    robot.clickOn("#editButton");
    robot.clickOn("#makeAd1");
    this.postAd();

    sleep(500);
  }

  /**
   * private helpmethod for one of the tests. 
   */
  private void testMakeClothingAd() {
    this.makeAd();
    robot.clickOn("#clothingButton");

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
    robot.clickOn("#editButton");
    robot.clickOn("#colourChoiceClothing");
    robot.clickOn("Red");
    robot.clickOn("#makeAd2");
    this.postAd();

    sleep(500);
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void testMakePropertyAd() {
    this.makeAd();
    robot.clickOn("#propertyButton");

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
    robot.clickOn("#editButton");
    robot.clickOn("#priceField3").write("0", 5);
    robot.clickOn("#makeAd21");
    this.postAd();

    sleep(500);
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void testMakeVehicleAd() {
    this.makeAd();
    robot.clickOn("#vehiclesButton");

    String adTitle = "Volvo XC90";
    String adDescription = "new Volvo for sale";
    String adPrice = "900000";
    String adType = "XC 90";
    String adYear = "2022";
    String adBrand = "Volvo";

    robot.clickOn("#titleField4").write(adTitle, 5);
    robot.clickOn("#descriptionArea4").write(adDescription, 5);
    robot.clickOn("#priceField4").write(adPrice, 5);
    robot.clickOn("#typeField4").write(adType, 5);
    robot.clickOn("#yearField4").write(adYear, 5);
    robot.clickOn("#brandField4").write(adBrand, 5);
    robot.clickOn("#makeAd22");
    this.closeAlert();

    robot.clickOn("#colourChoiceVehicles");
    robot.clickOn("White");
    robot.clickOn("#makeAd22");
    robot.clickOn("#editButton");
    robot.clickOn("#conditionField4");
    robot.clickOn("#makeAd22");
    this.postAd();

    sleep(500);
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void testMakeBookAd() {
    this.makeAd();
    robot.clickOn("#booksButton");

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
    robot.clickOn("#editButton");
    robot.clickOn("#pagesField5").write("0", 5);
    robot.clickOn("#makeAd23");
    this.postAd();

    sleep(500);
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void testYourProfile() {
    robot.clickOn("#yourProfile");
    sleep(500);
    robot.clickOn("Gucci pants");
    sleep(500);
    robot.clickOn("#goBack");
    sleep(500);
    robot.clickOn("#goBack1");
    sleep(500);
  }

  /**
   * private helpmethod for one of the tests.
   */
  private void logOut() {
    robot.clickOn("#logoutButton");
    sleep(1000);
  }

  @Test
  @Order(4)
  public void testBuyAndFilterAds() {
    robot.clickOn(regUsername).write(user1.getUsername(), 5);
    robot.clickOn(fullName).write(user1.getFullName(), 5);
    robot.clickOn(regPassword).write(user1.getPassword(), 5);
    robot.clickOn(repeatedRegPassword).write(user1.getRepeatedPassword(), 5);
    robot.clickOn(registerButton);
    this.closeAlert();

    robot.clickOn(usernameField).write(user1.getUsername(), 5);
    robot.clickOn(passwordField).write(user1.getPassword(), 5);
    robot.clickOn(loginButton);
    robot.clickOn("#clothingButton");
    robot.clickOn("#vehiclesButton");
    robot.clickOn("#electronicsButton");
    robot.clickOn("#clothingButton");
    robot.clickOn("#vehiclesButton");
    sleep(500);
    robot.clickOn("MacBook Pro 2022");
    sleep(500);
    robot.clickOn("#buyButton");
    robot.clickOn("#accept");
    this.closeAlert();
    sleep(500);
    robot.clickOn("#electronicsButton");
    robot.clickOn("#propertyButton");
    sleep(500);
    robot.clickOn("House for sale");
    sleep(500);
    robot.clickOn("#buyButton");
    robot.clickOn("#accept");
    this.closeAlert();
    sleep(500);
    robot.clickOn("#propertyButton");
    sleep(300);
    robot.clickOn("#removeFilters");
    robot.clickOn("#yourProfile");
    robot.clickOn("House for sale");
    robot.sleep(600);
    robot.clickOn("#goBack");
    sleep(300);
    robot.clickOn("#goBack1");
  }

  @Test
  @Order(5)
  public void testBuyAndSearchforAds() {
    robot.clickOn(regUsername).write(user2.getUsername(), 5);
    robot.clickOn(fullName).write(user2.getFullName(), 5);
    robot.clickOn(regPassword).write(user2.getPassword(), 5);
    robot.clickOn(repeatedRegPassword).write(user2.getRepeatedPassword(), 5);
    robot.clickOn(registerButton);
    this.closeAlert();

    robot.clickOn(usernameField).write(user2.getUsername(), 5);
    robot.clickOn(passwordField).write(user2.getPassword(), 5);
    robot.clickOn(loginButton);

    robot.clickOn("#searchBar").write("volvo", 5);
    robot.clickOn("Volvo XC90");
    sleep(500);
    robot.clickOn("#buyButton");
    robot.clickOn("#accept");
    this.closeAlert();
    sleep(500);
  }
}
