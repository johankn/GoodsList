package core;

import java.util.ArrayList;
import json.User;

/**
 * This class represents a user object that has been registeredin the app.
 * It will then create a user object with the same information that will be used in the app.
 */
public class RegisteredUser {

  private String username;
  private String password;
  private String fullName;
  private String repeatedPassword;

  /**
   * Constructor for creating a new RegisteredUser object.
   *
   * @param username username
   * @param password password
   * @param fullName fullname
   * @param repeatedPassword repeatedpassword
   */
  public RegisteredUser(
      String username, String password, String fullName, String repeatedPassword) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.repeatedPassword = repeatedPassword;
  }

  /**
   * Getter.
   *
   * @return boolean
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Getter.
   *
   * @return boolean
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Getter.
   *
   * @return boolean
   */
  public String getFullName() {
    return this.fullName;
  }

  /**
   * Getter.
   *
   * @return boolean
   */
  public String getRepeatedPassword() {
    return this.repeatedPassword;
  }

  /**
   * Generates new user object.
   *
   * @return boolean
   */
  public User generateUser() {
    return new User(
        this.getUsername(),
        this.getPassword(),
        this.getFullName(),
        new ArrayList<>(),
        new ArrayList<>());
  }
}
