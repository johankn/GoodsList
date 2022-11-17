package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class makes a jsonFileAsObject from the json-file and initiates various operations on the
 * JsonFileAsObject.
 */
public class DataObject {

  private ObjectMapper objectMapper;
  private JsonFileAsObject jsonFileAsObject;

  /** Constructor used when writing a user to file. */
  
  public DataObject(String filename) {
    objectMapper = new ObjectMapper();
    generateJsonFileAsObject(filename);
  }

  /**
   * Generates a jsonFileAsObject.java object from the jsonfil
   * using objectMapper.
   * 
   * @param filename
   */
  private void generateJsonFileAsObject(String filename) {
    try {
      String jsonString = makeJsonObjectFromJsonFile(filename).toString();
      jsonFileAsObject = objectMapper.readValue(jsonString, JsonFileAsObject.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a user to JsonFileAsObjectUserList.
   *
   * @param userToBeAdded user
   */
  public void addUserToJsonFileAsObjectUserList(User userToBeAdded) {
    jsonFileAsObject.addUser(userToBeAdded);
  }

  /**
   * Add an ad to the jsonFileAsObject.
   *
   * @param ad ad
   */
  public void addAdToJsonFileAsObject(Ad ad) {
    jsonFileAsObject.addAd(ad);
  }

  /**
   * Updates a User-object in the list of Users in JsonFileAsObject.
   *
   * @param userToBeUpdated user
   */
  public void updateUserActiveAds(User userToBeUpdated) {
    List<User> userList = jsonFileAsObject.getUsers();
    for (int i = 0; i < jsonFileAsObject.getUsers().size(); i++) {
      if (jsonFileAsObject.getUsers().get(i).getUsername().equals(userToBeUpdated.getUsername())) {
        userList.set(i, userToBeUpdated);
      }
    }
    jsonFileAsObject.setUsers(userList);
  }

  /**
   * Updates an ad to be sold when someone buys it, updates the list of ads as well.
   *
   * @param ad ad
   */
  public void updateAdInJsonFile(Ad ad) {
    List<Ad> ads = jsonFileAsObject.getAds();
    for (int i = 0; i < ads.size(); i++) {
      if (ads.get(i).getAdId().equals(ad.getAdId())) {
        ads.set(i, ad);
        System.out.println(ad.getIsSold());
      }
    }
    jsonFileAsObject.setAds(ads);
  }

  public void removeUsersAndAdsFromFile() {
    removeAllUsersFromFile();
    removeAllAdsFromFile();
  }

  /** Removes all users from the file. */
  private void removeAllUsersFromFile() {
    jsonFileAsObject.setUsers(new ArrayList<>());
  }

  /** Removes all ads from the file. */
  private void removeAllAdsFromFile() {
    jsonFileAsObject.setAds(new ArrayList<>());
  }

  /**
   * Helping method to make a JsonObject from a json-file as a string.
   *
   * @return JSONObject
   * @throws JSONException exception
   * @throws Exception exception
   */
  private JSONObject makeJsonObjectFromJsonFile(String filename) throws JSONException, Exception {
    JSONObject jsonObject = new JSONObject(readFileAsString(filename));
    return jsonObject;
  }

  /**
   * Helping method to convert a file to a string.
   *
   * @return String
   */
  public static String readFileAsString(String filename) throws Exception {
    String string = """

        """;
    string += new String(Files.readAllBytes(Paths.get(filename)), Charset.forName("UTF-8"));
    return string;
  }

  /**
   * Getter method.
   *
   * @return JsonFileAsObject
   */
  public JsonFileAsObject getJsonFileAsObject() {
    return jsonFileAsObject;
  }
}
