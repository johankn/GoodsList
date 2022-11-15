package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a user object used in the application and has all the information a user
 * needs.
 */
public class User {

  private List<String> myAds;
  private List<String> boughtAds;
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
      @JsonProperty(value = "myAds") List<String> myAds,
      @JsonProperty(value = "boughtAds") List<String> boughtAds) {
    this.fullname = fullname;
    this.username = username;
    this.password = password;
    this.myAds = myAds;
    this.boughtAds = boughtAds;
  }

  /**
   * Adds an ad to the users ad.
   *
   * @param ad ad
   */
  public void addAdToList(String ad) {
    myAds.add(ad);
  }

  /**
   * Method for buying an ad, adds the adID to the users list of adIDs.
   *
   * @param adId adId
   */
  public void buyAd(String adId) {
    if (this.myAds.contains(adId)) {
      throw new IllegalArgumentException("You cannot buy your own Ad!");
    } else {
      this.boughtAds.add(adId);
    }
  }

  /**
   * getter.
   *
   * @return list of ads
   */
  public List<String> getMyAds() {
    return new ArrayList<String>(myAds);
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
   * @param myAds myads
   */
  public void setMyAds(List<String> myAds) {
    this.myAds = myAds;
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

  /**
   * getter.
   *
   * @return list of boughtAds
   */
  public List<String> getBoughtAds() {
    return boughtAds;
  }

  /**
   * setter.
   *
   * @param boughtAds boughtAds
   */
  public void setBoughtAds(List<String> boughtAds) {
    this.boughtAds = boughtAds;
  }
}
