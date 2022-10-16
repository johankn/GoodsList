package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import core.RegisteredUser;

public class UserInfoCollectorTest {
    
    private UserInfoCollector userInfoCollector;
    private final String filename = "..//ui/src/test/resources/ui/userInfoCollector.json";
    private RegisteredUser regUser1;
    private RegisteredUser regUser2;

    @BeforeEach
    public void setup(){
        userInfoCollector = new UserInfoCollector();
        regUser1 = new RegisteredUser("tester123", "Test123","Tester Robot1" , "Test123" );
        regUser2 = new RegisteredUser("tester12", "Test12","Tester Robot2" , "Test12" );
    }

    @Test
    @DisplayName("Test if the method finds the correct user and if it throws exception if it is not found")
    public void testGetFullNameByUsername(){
        userInfoCollector.getFileOperator().writeNewUserDataToFile(filename, regUser1);
        userInfoCollector.getFileOperator().writeNewUserDataToFile(filename, regUser2);
        assertEquals("Tester Robot1", userInfoCollector.getFullNameByUsername(filename, "tester123"));
        assertEquals("Tester Robot2", userInfoCollector.getFullNameByUsername(filename, "tester12"));
        assertThrows(NullPointerException.class,
        () -> userInfoCollector.getFullNameByUsername(filename, "NotHere"));
    }

    @AfterEach
    @DisplayName("Removes all the users after a method is tested.")
    public void clearUsersInJsonFile(){
        userInfoCollector.getFileOperator().removeAllUsers(filename);
    }
}
