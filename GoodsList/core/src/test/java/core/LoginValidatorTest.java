package core;

import java.util.ArrayList;
import java.util.List;
import json.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for LoginValidatorTest.java. 
 */
public class LoginValidatorTest {

  private LoginValidator validator;

  private User user1;
  private List<User> listOfTestUsers;
  private User user2;

  /**
* Method to make objects for tests.
 */
  @BeforeEach
  public void setUp() {
    this.validator = new LoginValidator();
    listOfTestUsers = new ArrayList<>();
    user1 = new User("", "", "", new ArrayList<>(), new ArrayList<>());
    user2 = new User("testuser", "tester", "Tester TEst", new ArrayList<>(), new ArrayList<>());
    listOfTestUsers.add(user2);
  }

  @Test
  @DisplayName("Test if login is legal when username and/or password is empty.")
  public void testIfEmptyFields() {

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getPassword(), listOfTestUsers));

    user1.setPassword("Gutt123");
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getPassword(), listOfTestUsers));

    user1.setPassword("");
    user1.setUsername("Freddy");
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getPassword(), listOfTestUsers));
  }

  @Test
  @DisplayName(
      "Test if login is legal when password is wrong, and when it exists, but not for this"
          + " username.")
  public void testIsLoginLegalWhenPasswordIsNotMatching() {

    user1.setUsername("Freddy");
    user1.setPassword("ajfofnjqi2");

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getPassword(), listOfTestUsers));

    user1.setPassword("tester");
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getPassword(), listOfTestUsers));
  }

  @Test
  @DisplayName(
      "Test if Login is legal when the username and password are matching and exists in the list of"
          + " user.")
  public void testIsLoginLegalWhenUsernameAndPasswordMatch() {
    Assertions.assertTrue(
        validator.isLoginLegal(user2.getUsername(), user2.getPassword(), listOfTestUsers));
  }

  @Test
  @DisplayName("Test if login is legal when username does not exist")
  public void testIsLoginLegalWhenUserNameDoesNotExist() {
    user1.setUsername("itpDude");
    user1.setPassword("heyHEY");
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> validator.isLoginLegal(user1.getUsername(), user1.getUsername(), listOfTestUsers));
  }
}
