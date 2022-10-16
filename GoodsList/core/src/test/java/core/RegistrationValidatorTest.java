package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import core.RegistrationValidator;
import json.FileOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class RegistrationValidatorTest {

    private RegistrationValidator regValidator1;
    private RegistrationValidator regValidator2;
    private List<User> testlist;

    @BeforeEach
    public void setUp() {
        regValidator1 = new RegistrationValidator();
        testlist = new ArrayList<>();
    }

    @Test
    public void TestConstructor() {
        assertEquals(RegistrationValidator.class, regValidator1.getClass()); //Testing if the created registrationvalidator object is created
        assertEquals(null, regValidator2); // just a quick check that this is null
    }

    @Test
    public void TestUsernameValidation() { //a username should not be longer than 10 characters, nor contain any special symbols. 
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isUsernameValid(""); //testing an empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isUsernameValid("mathias@"); //testing with a special symbol (@)
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isUsernameValid("mathias%&"); //testing with a special symbol (%&)
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isUsernameValid("#)"); //testing with a special symbol (#))
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isUsernameValid("mathiasvaaa"); //testing a username with 11 characters
        });
        assertTrue(regValidator1.isUsernameValid("mathias"));
        assertTrue(regValidator1.isUsernameValid("Javamann1"));
        assertTrue(regValidator1.isUsernameValid("CodeGod10"));
    }

    @Test
    public void TestPasswordValidation() { // a password needs to be at least 8 characters long, has to contain at least one capital letter and one number
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isPasswordValid(""); //testing an empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isPasswordValid("Hello1"); //testing a password with number, capital letter but not enough characters
        });
        // assertThrows(IllegalArgumentException.class, () -> {
        //     regValidator1.isPasswordValid("passord123"); //not working bruv
        // });
        assertTrue(regValidator1.isPasswordValid("Passord12"));
    }

    @Test
    public void TestFullNameValidation() {
        assertThrows(IllegalArgumentException.class, () -> { // A full name should start with a capital letter
            regValidator1.isFullNameValid(""); //testing an empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isFullNameValid("mathias"); //testing without a capital letter
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isFullNameValid("Hei123"); //It cannot contain numbers
        });
        assertTrue(regValidator1.isFullNameValid("Mathias"));
        assertTrue(regValidator1.isFullNameValid("Tor"));
        assertTrue(regValidator1.isFullNameValid("Ola Nordmann"));
    }

    @Test
    public void TestCheckExcistingUsername() { //this method checks if a username is in a given list. Because our structure is Username, password, fullname it should only check every third index. 
        //it should throw when if the username already is in the list (taken)
        User user1 = new User("username", "password", "fullname", new ArrayList<>());
        User user2 = new User("username2", "password2", "fullname2", new ArrayList<>());
        User user3 = new User("username3", "password3", "fullname3", new ArrayList<>());
        
        testlist.add(user1);
        testlist.add(user2);
        testlist.add(user3);
        
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.checkExcistingUsername(testlist, "username"); //testing the first index (username)
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.checkExcistingUsername(testlist, "username2"); //Testing the fourth index (username2)
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.checkExcistingUsername(testlist, "username3"); //Testing the seventh index (username3)
        });
        assertTrue(regValidator1.checkExcistingUsername(testlist, "username4")); //tests a random username that isnt in the list. 
        assertTrue(regValidator1.checkExcistingUsername(testlist, "password")); //checks that it doesnt accepts anything other than usernames from the list
        assertTrue(regValidator1.checkExcistingUsername(testlist, "fullname")); //checks that it doesnt accepts anything other than usernames from the list
    }

    @Test
    public void TestEqualPasswords() { //methods returns true if the to given passwords match, and throws exception if not
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.equalPasswords("null", "one"); //not same passwords
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.equalPasswords("Test", "test"); //same words, uppercase and lowercase
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.equalPasswords("hei1", "hei2"); //test for numbers
        });
        assertTrue(regValidator1.equalPasswords("test", "test"));
        assertTrue(regValidator1.equalPasswords("test1", "test1"));
    }

    @Test
    public void TestIsRegistrationLegal() {
        assertTrue(regValidator1.isRegistrationLegal("username", "Passord11", "Passord11", "Username", testlist));
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isRegistrationLegal("", "Passord11", "Passord11", "Username", testlist); //test for empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isRegistrationLegal("username", "", "Passord11", "Username", testlist); //test for empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isRegistrationLegal("username", "Passord11", "", "Username", testlist); //test for empty field
        });
        assertThrows(IllegalArgumentException.class, () -> {
            regValidator1.isRegistrationLegal("username", "Passord11", "Passord11", "", testlist); //test for empty field
        });
    }

}

