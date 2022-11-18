package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for making the jsonfile to an object.
 */

public class JsonFileAsObject {

  /**
   * This class represents a jsonfiule with list of users as a java object.
   */
  private List<Ad> ads;
  private List<User> users;

  /**
   * JsonCreator to make jackson understand that this constructor should be used.
   * JsonProperty to tell json what to look for in json file when make this object
   */
  @JsonCreator
  public JsonFileAsObject(@JsonProperty(value = "users") List<User> users,
      @JsonProperty(value = "ads") List<Ad> ads) {
    this.users = users;
    this.ads = ads;
  }

  /**
   * Return the users.
   *
   * @return users
   */
  public List<User> getUsers() {
    return new ArrayList<User>(users);
  }

  /**
   * Setter for users.
   *
   * @param users users
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * Adds a user.
   *
   * @param user user
   */
  public void addUser(User user) {
    users.add(user);
  }

  /**
   * Setter for ads.
   *
   * @param ads ads
   */
  public void setAds(List<Ad> ads) {
    this.ads = ads;
  }

  /**
   * Getter for ads.
   *
   * @return ads
   */
  public List<Ad> getAds() {
    return new ArrayList<>(this.ads);
  }

  /**
   * Adding an Ad to ads-list.
   *
   * @param ad ad
   */
  public void addAd(Ad ad) {
    this.ads.add(ad);
  }
}