package json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Test class for FileOperator.java. */
public class FileOperatorTest {

  private FileOperator fileOperator;
  private final String filename = "..//ui/src/test/resources/ui/fileOperatorTest.json";

  @BeforeEach
  public void setup() {
    fileOperator = new FileOperator();
  }

  @Test
  @DisplayName("Test writing to jsonfile")
  public void testwriteNewUserDataToFile() {
    User user1 =
        new User("tester123", "Test123", "Tester Robot", new ArrayList<>(), new ArrayList<>());

    // Writes user to file
    fileOperator.writeNewUserDataToFile(filename, user1);
    // Reads all users in file
    List<User> usersInFile = fileOperator.getAllUsersAsList(filename);

    // Tests the content of the users in the file
    assertEquals(1, usersInFile.size());
    assertEquals("tester123", usersInFile.get(0).getUsername());
    assertEquals("Test123", usersInFile.get(0).getPassword());
    assertEquals("Tester Robot", usersInFile.get(0).getFullname());
    assertTrue(usersInFile.get(0) instanceof User);

    //Writes new user to file
    User user2 =
        new User("tester1234", "Test1234", "Tester Robot2", new ArrayList<>(), new ArrayList<>());
    fileOperator.writeNewUserDataToFile(filename, user2);
    usersInFile = fileOperator.getAllUsersAsList(filename);

    // Tests the content of the users in the file
    assertEquals("tester1234", usersInFile.get(1).getUsername());
    assertEquals("Test1234", usersInFile.get(1).getPassword());
    assertEquals("Tester Robot2", usersInFile.get(1).getFullname());
    assertTrue(usersInFile.get(1) instanceof User);
  }

  @Test
  @DisplayName("Test updating a User in json-file")
  public void testUpdateUserObjectJsonFile() {
    // Writes users to file
    User user1 =
        new User("tester123", "Test123", "Tester Robot", new ArrayList<>(), new ArrayList<>());
    User user2 =
        new User("tester1234", "Test1234", "Tester Robot 1", new ArrayList<>(), new ArrayList<>());
    fileOperator.writeNewUserDataToFile(filename, user1);
    fileOperator.writeNewUserDataToFile(filename, user2);

    assertTrue(fileOperator.getAllUsersAsList(filename).get(0).getMyAds().size() == 0);

    //Making ad for the user
    Product product = new Electronics(1200, "new", "Apple", "Airpods");
    Ad ad =
        new Ad(
            "Golf club for sale!", product, "12.10.22", "Very nice airpods, brand new", "1", false);
    //Adds the ad to users-ad-list
    user1.addAdToList("1");


    //updates the user in jsionfile
    fileOperator.updateUserObjectJsonFile(filename, user1);
    List<User> usersInFile = fileOperator.getAllUsersAsList(filename);

    // Different testcases:
    assertTrue(usersInFile.size() == 2);
    assertEquals("tester123", usersInFile.get(0).getUsername());
    assertEquals("Test123", usersInFile.get(0).getPassword());
    assertEquals("Tester Robot", usersInFile.get(0).getFullname());
    assertTrue(usersInFile.get(0) instanceof User);
    assertTrue(usersInFile.get(0).getMyAds().size() == 1);
    assertEquals("1", usersInFile.get(0).getMyAds().get(0));
  }

  @Test
  @DisplayName("Test reading from json-file")
  public void testGetAllUsersAsList() {

    // Users to be written to file
    User user2 =
        new User("tester1", "Test123", "Tester Robot", new ArrayList<>(), new ArrayList<>());
    User user3 =
        new User("tester2", "Test1234", "Tester Girl", new ArrayList<>(), new ArrayList<>());
    User user4 =
        new User("tester3", "Test12345", "Tester Guy", new ArrayList<>(), new ArrayList<>());

    // Writes users to file
    fileOperator.writeNewUserDataToFile(filename, user2);
    fileOperator.writeNewUserDataToFile(filename, user3);
    fileOperator.writeNewUserDataToFile(filename, user4);


    //List of the users in jsonfile
    List<User> listOfUsers = fileOperator.getAllUsersAsList(filename);

    // testcases
    assertNotNull(listOfUsers);
    assertEquals("tester1", listOfUsers.get(0).getUsername());
    assertEquals("Test123", listOfUsers.get(0).getPassword());
    assertEquals("tester2", listOfUsers.get(1).getUsername());
    assertEquals("Test1234", listOfUsers.get(1).getPassword());
    assertEquals("tester3", listOfUsers.get(2).getUsername());
    assertEquals(3, listOfUsers.size());
  }

  @Test
  @DisplayName("Tests getting all ads in file ")
  public void testGetAllAdsInFile() {
    //Ads to be written to file
    Product product1 = new Electronics(1200, "new", "Apple", "Airpods");
    Ad ad1 = new Ad("Golf club for sale!", product1, "12.10.22", "Very nice airpods", "1", false);

    Product product2 = new Books(300, "Good", "Jo Nesbø", "Drama", 2012, 245);
    Ad ad2 =
        new Ad(
            "Book for sale", product2, "12.10.22", "Very nice book, almost brand new", "2", false);

    //Writes the ads to file
    fileOperator.addAdToFile(filename, ad1);
    fileOperator.addAdToFile(filename, ad2);

    assertEquals(2, fileOperator.getAllAdsInFile(filename).size());
    assertEquals("Golf club for sale!", fileOperator.getAllAdsInFile(filename).get(0).getAdTitle());
    assertEquals("Book for sale", fileOperator.getAllAdsInFile(filename).get(1).getAdTitle());
    assertNotNull(fileOperator.getAllAdsInFile(filename).size());
  }

  @Test
  @DisplayName("Test for updating an Ad in Json-file")
  public void testUpdateAdObjectJsonFile() {
    // Creating an adObject that is not sold
    Product product1 = new Electronics(1200, "new", "Apple", "Airpods");
    Ad ad1 =
        new Ad(
            "Golf club for sale!",
            product1,
            "12.10.22",
            "Very nice airpods, brand new",
            "1",
            false);
    
    //another ad object
    Product product2 = new Electronics(120, "old", "Samsung", "Airpods");
    Ad ad2 = new Ad(
        "Airpods!",
        product2,
        "12.10.22",
        "Very nice airpods, brand old",
        "2",
        false);

    // Writing the adObject to file
    fileOperator.addAdToFile(filename, ad1);
    fileOperator.addAdToFile(filename, ad2);
    List<Ad> adsInFile = fileOperator.getAllAdsInFile(filename);

    // Checks if the isSold-field is false
    assertEquals(false, adsInFile.get(0).getIsSold());

    // Setting the isSold-field for the ad as true.
    ad1.setIsSold(true);
    ad1.setAdTitle("Brans new Golf club for sale");
    fileOperator.updateAdObjectJsonFile(filename, ad1);
    adsInFile = fileOperator.getAllAdsInFile(filename);
    // Checks if the isSold-field and adTitle-fiels is updated in json-file
    assertEquals(true, adsInFile.get(0).getIsSold());
    assertEquals("Brans new Golf club for sale", adsInFile.get(0).getAdTitle());

    //Checks tha ad2 aint edited
    assertEquals(false, adsInFile.get(1).getIsSold());

    // Checking that it only is one Ad in the file
    assertEquals(2, adsInFile.size());
  }

  @Test
  @DisplayName("Test for removing all data in the Json-file")
  public void testRemoveAllDataFromFile() {
    // Making data to add to the file
    User user1 =
        new User("tester123", "Test123", "Tester Robot", new ArrayList<>(), new ArrayList<>());

    // Writing ad and user to file
    fileOperator.writeNewUserDataToFile(filename, user1);

    // Checks if a user is written to file
    assertEquals(1, fileOperator.getAllUsersAsList(filename).size());

    // removes all data from file
    fileOperator.removeAllDataFromFile(filename);

    // Checks if user is removed from file
    assertEquals(0, fileOperator.getAllUsersAsList(filename).size());

    // Ad for used for the test
    Product product1 = new Electronics(1200, "new", "Apple", "Airpods");
    Ad ad1 =
        new Ad(
            "Golf club for sale!",
            product1,
            "12.10.22",
            "Very nice airpods, brand new",
            "1",
            false);

    fileOperator.addAdToFile(filename, ad1);

    // Checks if a ad is written to file
    assertEquals(1, fileOperator.getAllAdsInFile(filename).size());

    // removes all data from file
    fileOperator.removeAllDataFromFile(filename);

    // Checks if ad is removed from file
    assertEquals(0, fileOperator.getAllAdsInFile(filename).size());

    // Writes user and ad to file
    fileOperator.writeNewUserDataToFile(filename, user1);
    fileOperator.addAdToFile(filename, ad1);

    // Checks if the ad and user is in the file
    assertEquals(1, fileOperator.getAllAdsInFile(filename).size());
    assertEquals(1, fileOperator.getAllUsersAsList(filename).size());
    // Removing all data in the file
    fileOperator.removeAllDataFromFile(filename);

    // Checking if all the data is removed from file
    assertEquals(0, fileOperator.getAllUsersAsList(filename).size());
    assertEquals(0, fileOperator.getAllAdsInFile(filename).size());
  }

  @Test
  @DisplayName("Test for adding an Ad in Json-file")
  public void testAddAdToFile() {

    //Ads to be written to file
    Product product1 = new Electronics(1200, "new", "Apple", "Airpods");
    Ad ad1 = new Ad("Airpods", product1, "12.10.22", "Very nice airpods, brand new", "1", false);

    Product product2 = new Books(300, "Good", "Jo Nesbø", "Drama", 2012, 245);
    Ad ad2 = new Ad(
        "Book for sale", product2, "12.10.22", "Very nice book, almost brand new", "2", false);

    //Writing the ads to file
    fileOperator.addAdToFile(filename, ad1);
    fileOperator.addAdToFile(filename, ad2);

    //List of ads in file
    List<Ad> adsInFile = fileOperator.getAllAdsInFile(filename);

    assertEquals(2, adsInFile.size());
    assertEquals("new", adsInFile.get(0).getProduct().getCondition());
    assertEquals("Book for sale", adsInFile.get(1).getAdTitle());
  }
  
  @Test
  @DisplayName("test to get JsonFileAsObject")
  public void testGetJsonFileAsObject() {
    //Checks that the jsonFileAsObject is not null
    assertNotNull(fileOperator.getJsonFileAsObject(filename));
    assertEquals(0, fileOperator.getJsonFileAsObject(filename).getUsers().size());
  }

  @Test
  @DisplayName("Test for getting Objectmapper")
  public void getObjectMapper() {
    assertNotNull(fileOperator.getObjectMapper());
    assertEquals(ObjectMapper.class, fileOperator.getObjectMapper().getClass());
  }

  @Test
  @DisplayName("Test for generating filename. Should generate a filename that goes to user.home. Since it is different for each individual computer i just test if it generates a string.")
  public void testGenerateFilename() throws IOException {
    assertEquals(String.class, fileOperator.generateFilename().getClass());
    assertTrue(fileOperator.generateFilename() != "");
  }

  @Test
  @DisplayName("Test for the jsonfileinitialization, which happens if ts the first time a user runs the app.")
  public void testInitialize() {
    fileOperator.initializeJsonFile(filename, new JsonFileAsObject(new ArrayList<>(), new ArrayList<>()));
    assertEquals(0, fileOperator.getAllUsersAsList(filename).size());
    assertEquals(0, fileOperator.getAllAdsInFile(filename).size());
  }
  

  @AfterEach
  @DisplayName("Removes all the users after a method is tested.")
  public void clearUsersInJsonFile() {
    fileOperator.removeAllDataFromFile(filename);
  }
}
