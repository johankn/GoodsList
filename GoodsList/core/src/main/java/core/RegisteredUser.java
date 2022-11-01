package core;

import java.util.ArrayList;

import json.User;

/**
 * This class represents a registered user in the app.
 */
public class RegisteredUser {

  private String username;
  private String password;
  private String fullName;
  private String repeatedPassword;

  /**
   * constructor.
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
   * getter.
   *
   * @return boolean
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * getter.
   *
   * @return boolean
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * getter.
   *
   * @return boolean
   */
  public String getFullName() {
    return this.fullName;
  }

  /**
   * getter.
   *
   * @return boolean
   */
  public String getRepeatedPassword() {
    return this.repeatedPassword;
  }

  /**
   * generates new user.
   *
   * @return boolean
   */
  public User generateUser() {
    return new User(this.getUsername(), this.getPassword(), this.getFullName(), new ArrayList<>(), new ArrayList<>());
  }
}
