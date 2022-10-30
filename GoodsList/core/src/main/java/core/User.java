package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import json.Ad;
/**
* This class represents a user in the app.
*/

public class User {

  private List<Ad> activeAds;
  private String fullname;
  private String username;
  private String password;

  /**
   * A constructor for the class User We are using Jackson Annotation to create executing rules for
   * jackson, JsonCreator specifies that this is a constructor. JsonProperty specifies which fields
   * should be set to what in the JSON-file
   */
  @JsonCreator
  public User(
      @JsonProperty(value = "username") String username,
      @JsonProperty(value = "password") String password,
      @JsonProperty(value = "fullname") String fullname,
      @JsonProperty(value = "activeAds") List<Ad> activeAds) {
    this.fullname = fullname;
    this.username = username;
    this.password = password;
    this.activeAds = activeAds;
  }

  /**
   * ads an ad to the users ad. 
   *
   * @param ad ad
   */
  public void addAdToList(Ad ad) {
    activeAds.add(ad);
  }

  /**
   * getter.
   *
   * @return list of ads
   */
  public List<Ad> getActiveAds() {
    return new ArrayList<Ad>(activeAds);
  }

  /**
   * getter.
   *
   * @return fullname
   */
  public String getFullname() {
    return fullname;
  }

  /**
   * getter.
   *
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * getter.
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * setter.
   *
   * @param activeAds activeads
   */
  public void setActiveAds(List<Ad> activeAds) {
    this.activeAds = activeAds;
  }

  /**
   * setter.
   *
   * @param fullname fullname
   */
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  /**
   * setter.
   *
   * @param username username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * setter.
   *
   * @param password password
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
