package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class makes a jsonFileAsObject from the json-file
 *  and initiates various operations on the JsonFileAsObject.
 */
public class DataObject {

  private ObjectMapper objectMapper;
  private User user;
  private Ad ad;
  private boolean addUser;
  private boolean addAd;
  private boolean removeAllUsers;
  private JsonFileAsObject jsonFileAsObject;

  /**
   *Constructor used when wrinting to a file.
  */
  public DataObject(String filename, User user, boolean addUser) {
    this.user = user;
    this.addUser = addUser;
    objectMapper = new ObjectMapper();
    generateJsonFileAsObject(filename);
  }

  public DataObject(String filename, User user, Ad ad, boolean addAd) {
    this.user = user;
    this.ad = ad;
    this.addAd = addAd;
    objectMapper = new ObjectMapper();
    generateJsonFileAsObject(filename);
  }

  /**
   * Constructor used when reading a file.
   */
  public DataObject(String filename, boolean removeAllUsers) {
    this.removeAllUsers = removeAllUsers;
    objectMapper = new ObjectMapper();
    generateJsonFileAsObjectForReadingAndRemoving(filename);
  }

  /**
   *Generates a JsonFileAsObject from the json-file, used for appending a new user or updating
   *an existitng user in dataObjects.json.
   */
  private void generateJsonFileAsObject(String filename) {
    try {
      String jsonString = makeJsonObjectFromJsonFile(filename).toString();
      jsonFileAsObject = objectMapper.readValue(jsonString, JsonFileAsObject.class);
      if (addUser) {
        addUserToJsonFileAsObjectUserList(user);
      } else if (!addUser) {
        updateUserActiveAds(user);
        if (addAd) {
          addAdToJsonFileAsObject(ad);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a user to JsonFileAsObjectUserList.
   *
   * @param userToBeAdded user
   */
  private void addUserToJsonFileAsObjectUserList(User userToBeAdded) {
    jsonFileAsObject.addUser(userToBeAdded);
  }

  private void addAdToJsonFileAsObject(Ad ad) {
    jsonFileAsObject.addAd(ad);
  }

  /**
   * Updates a User-object in the list of Users in JsonFileAsObject.
   *
   * @param userToBeUpdated user
   */
  private void updateUserActiveAds(User userToBeUpdated) {
    List<User> userList = jsonFileAsObject.getUsers();
    for (int i = 0; i < jsonFileAsObject.getUsers().size(); i++) {
      if (jsonFileAsObject.getUsers().get(i).getUsername().equals(userToBeUpdated.getUsername())) {
        userList.set(i, userToBeUpdated);
      }
    }
    jsonFileAsObject.setUsers(userList);
  }

  /**
   * Generates a JsonFileAsObject from the json-file, used for reading content of dataObjects.json.
   */
  private void generateJsonFileAsObjectForReadingAndRemoving(String filename) {
    try {
      String jsonString = makeJsonObjectFromJsonFile(filename).toString();
      jsonFileAsObject = objectMapper.readValue(jsonString, JsonFileAsObject.class);
      if (removeAllUsers) {
        removeAllUsersFromFile();
        removeAllAdsFromFile();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void removeAllUsersFromFile() {
    jsonFileAsObject.setUsers(new ArrayList<>());
  }

  private void removeAllAdsFromFile() {
    jsonFileAsObject.setAds(new ArrayList<>());
  }

  /**
   * Help methood to make a JsonObject from a json-file as a string.
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
   * Help method to convert a file to a string.
   *
   * @return String
   */
  private String readFileAsString(String filename) throws Exception {
    String string = """
        
        """;
    string += new String(Files.readAllBytes(Paths.get(filename)));
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
