package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class FileOperatorTest {

    private FileOperator fileOperator;
    private RegisteredUser testUser1;
    private File testFile;
    private long lengthOfTestFileBeforeWritingTheNewUser;
    private List<String> usersList;

    public void makeTestFileWithJsonObject() {
        testFile = new File("dataObjectsTestFile.json");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject userInfo1 = new JSONObject();
        userInfo1.put("username", "mavenLover");
        userInfo1.put("password", "Maven123");
        userInfo1.put("fullname", "Maven Plugin");
        jsonArray.put(userInfo1);
        jsonObject.put("users", jsonArray);
        // TODO:Consider moving this to another method in FileOperator.java
        try {
            FileWriter fileWriter = new FileWriter("dataObjectsTestFile.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        lengthOfTestFileBeforeWritingTheNewUser = testFile.length();
    }

    @Test
    @DisplayName("Test if writeUserDataToFile writes users to json-file")
    public void testWriteUserDataToFile() {
        fileOperator = new FileOperator();
        makeTestFileWithJsonObject();
        testUser1 = new RegisteredUser("vscode", "Docker123", "Visual Studio Code", "Docker123");
        fileOperator.writeUserDataToFile("dataObjectsTestFile.json", testUser1);

        // Tests if the file has been changed
        assertFalse(testFile.length() == lengthOfTestFileBeforeWritingTheNewUser);
        List<String> allUsersAfterTheFileHasBeenWrittenTo = fileOperator.getAllUsersAsList("dataObjectsTestFile.json");
        assertEquals("mavenLover", allUsersAfterTheFileHasBeenWrittenTo.get(0));
        assertEquals("Maven123", allUsersAfterTheFileHasBeenWrittenTo.get(1));
        assertEquals("Maven Plugin", allUsersAfterTheFileHasBeenWrittenTo.get(2));
        assertEquals("vscode", allUsersAfterTheFileHasBeenWrittenTo.get(3));
        assertEquals("Docker123", allUsersAfterTheFileHasBeenWrittenTo.get(4));
        assertEquals("Visual Studio Code", allUsersAfterTheFileHasBeenWrittenTo.get(5));
        assertNotEquals("", allUsersAfterTheFileHasBeenWrittenTo.get(5));
        
    }

    @Test
    @DisplayName("Test if getAllUsersAsList is returning the correct List")
    public void testGetAllUsersAsList() {
        fileOperator = new FileOperator();
        makeTestFileWithJsonObject();
        usersList = fileOperator.getAllUsersAsList("dataObjectsTestFile.json");
        assertEquals("mavenLover", usersList.get(0));
        assertEquals("Maven123", usersList.get(1));
        assertEquals("Maven Plugin", usersList.get(2));
        assertEquals(3, usersList.size());

    }

    @AfterEach
    public void deleteTestFile(){
        testFile.delete();
    }

}
