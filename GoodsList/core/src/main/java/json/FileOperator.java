package json;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.RegisteredUser;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all operations connected to the json file. 
 */
public class FileOperator {

  /*
   * This Class read from and writes to a json file.
   */

  private ObjectMapper objectMapper;
  private ObjectWriter objectWriter;
  private DataObject dataObject;

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
  // Writes a user to the json-file
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
   * Gets all the users in a json-file. Format: [User1, User2, ..., User_j]
   *
   * @param filename filename
   * @return user
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
  public void removeAllUsers(String filename) {
    dataObject = new DataObject(filename, true);
    try {
      objectWriter.writeValue(Paths.get(filename).toFile(), dataObject.getJsonFileAsObject());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * gets all the ads in the a json-file.
   *
   * @param filename filename
   * @return ad
   */
  public List<Ad> getAllAdsInFile(String filename) {
    List<User> listOfUsers = getAllUsersAsList(filename);
    List<Ad> ads = new ArrayList<>();
    for (User user : listOfUsers) {
      for (Ad ad : user.getActiveAds()) {
        ads.add(ad);
      }
    }
    return ads;
  }
}
