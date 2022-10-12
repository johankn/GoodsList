package core;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class LoginValidatorTest {
    //private static final String filename = "..//core/src/main/java/json/dataObjects.json";
    private LoginValidator validator;
    //private FileOperator fileOperator;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() {
        this.validator = new LoginValidator();
    }

    @Test
    @DisplayName("Test if login is legal when username and/or password is empty.")
    public void testIfEmptyFields() {
        this.username = "";
        this.password = "gutt123";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));

        this.username = "Freddy";
        this.password = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));

        this.username = "";
        this.password = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));
    }

    @Test
    @DisplayName("Test if login is legal when password is wrong, and when it exists, but not for this username.")
    public void testIsLoginLegalWhenPasswordIsNotMatching() {
        this.username = "Freddy";
        this.password = "ajfofnjqi2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));

        this.username = "Freddy";
        this.password = "werwew";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));
    }

    @Test
    @DisplayName("Test if Login is legal when the username and password are matching and exists in the json file.")
    public void testIsLoginLegalWhenUsernameAndPasswordMatch() {
        this.username = "Freddy";
        this.password = "gutt123";
        Assertions.assertTrue(validator.isLoginLegal(username, password));
        Assertions.assertDoesNotThrow(() -> validator.isLoginLegal(username, password));
    }

    @Test
    @DisplayName("Test if login is legal when username does not exist")
    public void testIsLoginLegalWhenUserNameDoesNotExist() {
        this.username = "itpDude";
        this.password = "heyHEY";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.isLoginLegal(username, password));
    }

}
