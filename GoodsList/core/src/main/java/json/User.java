package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
/**
* This class represents a user in the app.
*/

public class User {

  private List<Integer> myAds;
  private List<Integer> boughtAds;
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
      @JsonProperty(value = "myAds") List<Integer> myAds,
      @JsonProperty(value = "boughtAds") List<Integer> boughtAds
      ) {
    this.fullname = fullname;
    this.username = username;
    this.password = password;
    this.myAds = myAds;
    this.boughtAds = boughtAds;
    
  }

  /**
   * ads an ad to the users ad. 
   *
   * @param ad ad
   */
  public void addAdToList(Integer ad) {
    myAds.add(ad);
  }

  /**
   * getter.
   *
   * @return list of ads
   */
  public List<Integer> getMyAds() {
    return new ArrayList<Integer>(myAds);
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
  public void setMyAds(List<Integer> myAds) {
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
  public List<Integer> getBoughtAds() {
    return boughtAds;
  }
  
  /**
   * setter.
   *
   * @param boughtAds boughtAds
   */
  public void setBoughtAds(List<Integer> boughtAds) {
    this.boughtAds = boughtAds;
  }
  
}
