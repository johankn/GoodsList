package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import core.Ad;
import core.Electronics;
import core.FileOperator;
import core.Product;
import core.RegisteredUser;
import core.User;



public class FileOperatorTest {
    
    private FileOperator fileOperator;
    private final String filename = "src/test/java/json/fileOperatorTest.json";
//"/Users/eliaslysosommerseth/Documents/NTNU/2-klasse/1-semester/itp1/gruppe-//rosjekt/gr2226-1/GoodsList/core/src/test/java/json/fileOperatorTest.json"

    @BeforeEach
    public void setup(){
        fileOperator = new FileOperator();
    }

    @Test
    @DisplayName("Test writing to jsonfile")
    public void testwriteNewUserDataToFile(){
        RegisteredUser regUser1 = new RegisteredUser("tester123", "Test123","Tester Robot" , "Test123" );
        
        //Writes regUser1 to file 
        fileOperator.writeNewUserDataToFile(filename, regUser1);
        //Reads all users in file
        List<core.User> usersInFile = fileOperator.getAllUsersAsList(filename);

        //Tests the content of the users in the file
        assertEquals(1, usersInFile.size());
        assertEquals("tester123", usersInFile.get(0).getUsername());
        assertEquals("Test123", usersInFile.get(0).getPassword());
        assertEquals("Tester Robot", usersInFile.get(0).getFullname());
        assertTrue(usersInFile.get(0) instanceof User);
    }

    @Test
    @DisplayName("Test updating a User in json-file")
    public void testUpdateUserObjectJsonFile(){
        //Writes a registrated user to file
        RegisteredUser regUser1 = new RegisteredUser("tester123", "Test123","Tester Robot" , "Test123" );
        fileOperator.writeNewUserDataToFile(filename, regUser1);

        //making a Userobject that represents the same user as regUser1 with an Ad.
        List<Ad> listOfAds = new ArrayList<>();
        Product product = new Electronics(1200, "new", "Apple", "Airpods");
        Ad ad = new Ad("Golf club for sale!", product, "12.10.22", "Very nice airpods, brand new");
        User user1 = new User("tester123", "Test123" , "Tester Robot" ,listOfAds);
        user1.addAdToList(ad);

        fileOperator.updateUserObjectJsonFile(filename, user1);
        List<core.User> usersInFile = fileOperator.getAllUsersAsList(filename);

        //Different testcases:
        assertTrue(usersInFile.size() == 1);
        assertEquals("tester123", usersInFile.get(0).getUsername());
        assertEquals("Test123", usersInFile.get(0).getPassword());
        assertEquals("Tester Robot", usersInFile.get(0).getFullname());
        assertTrue(usersInFile.get(0) instanceof User);
        assertTrue(usersInFile.get(0).getActiveAds().size() == 1);
    }

    @Test
    @DisplayName("Test reading from json-file")
    public void testGetAllUsersAsList(){

        //Users to be written to file
        RegisteredUser regUser2 = new RegisteredUser("tester1", "Test123","Tester Robot" , "Test123" );
        RegisteredUser regUser3 = new RegisteredUser("tester2", "Test1234","Tester Girl" , "Test1234" );
        RegisteredUser regUser4 = new RegisteredUser("tester3", "Test12345","Tester Guy" , "Test12345" );

        //Writes users to file
        fileOperator.writeNewUserDataToFile(filename, regUser2);
        fileOperator.writeNewUserDataToFile(filename, regUser3);
        fileOperator.writeNewUserDataToFile(filename, regUser4);

        List<User> listOfUsers = fileOperator.getAllUsersAsList(filename);


        //testcases
        assertNotNull(listOfUsers);
        assertEquals("tester1", listOfUsers.get(0).getUsername());
        assertEquals("tester2", listOfUsers.get(1).getUsername());
        assertEquals("tester3", listOfUsers.get(2).getUsername());
        assertEquals(3, listOfUsers.size());
    }

    @AfterEach
    @DisplayName("Removes all the users after a method is tested.")
    public void clearUsersInJsonFile(){
        fileOperator.removeAllUsers(filename);
    }
}
