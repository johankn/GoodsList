package core;

import java.util.ArrayList;

public class RegisteredUser {

  /*
   * This class represents a registered user in the app.
   */

  private String username;
  private String password;
  private String fullName;
  private String repeatedPassword;

  public RegisteredUser(
      String username, String password, String fullName, String repeatedPassword) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.repeatedPassword = repeatedPassword;
  }

  /**
   * @return String
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * @return String
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * @return String
   */
  public String getFullName() {
    return this.fullName;
  }

  /**
   * @return String
   */
  public String getRepeatedPassword() {
    return this.repeatedPassword;
  }

  /**
   * @return User
   */
  public User generateUser() {
    return new User(this.getUsername(), this.getPassword(), this.getFullName(), new ArrayList<>());
  }
}
