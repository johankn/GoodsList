package json;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.RegisteredUser;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class that handles all operations connected to the json file. Reads from and writes to the json
 * file.
 */
public class FileOperator {

  private ObjectMapper objectMapper;
  private ObjectWriter objectWriter;
  private DataObject dataObject;

  /** A constructor that initializes the FileOperator object. */
  public FileOperator() {
    objectMapper = new ObjectMapper();
    objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
  }

  /**
   * Writes a new user to json file.
   *
   * @param filename filename
   * @param registeredUser user
   */
  public void writeNewUserDataToFile(String filename, RegisteredUser registeredUser) {
    dataObject = new DataObject(filename, registeredUser.generateUser(), true);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes update of an existing user with the new information.
   *
   * @param filename filename
   * @param user user
   */
  public void updateUserObjectJsonFile(String filename, User user) {
    dataObject = new DataObject(filename, user, false);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes an object to json file.
   *
   * @param filename filename
   * @param ad ad
   */
  public void updateAdObjectJsonFile(String filename, Ad ad) {
    dataObject = new DataObject(filename, ad, false);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets all the users in a json-file. Format: [User1, User2, ..., User_j]
   *
   * @param filename filename
   * @return list of users from file
   */
  public List<User> getAllUsersAsList(String filename) {
    dataObject = new DataObject(filename, false);
    List<User> list = dataObject.getJsonFileAsObject().getUsers();
    return list;
  }

  /**
   * Removes all users from a json file.
   *
   * @param filename filename
   */
  public void removeAllDataFromFile(String filename) {
    dataObject = new DataObject(filename, true);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets all the ads in the a json-file.
   *
   * @param filename filename
   * @return list of ads from file
   */
  public List<Ad> getAllAdsInFile(String filename) {
    dataObject = new DataObject(filename, false);
    List<Ad> ads = dataObject.getJsonFileAsObject().getAds();
    return ads;
  }

  /**
   * Adds the ad to the file.
   *
   * @param filename filename
   * @param ad ad
   */
  public void addAdToFile(String filename, Ad ad) {
    dataObject = new DataObject(filename, ad, true);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
