package core;

import json.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RegisteredUserTest {
    
  private RegisteredUser registeredUser;

  @BeforeEach
  public void setUp() {
    this.registeredUser = new RegisteredUser("testUser", "Password1", "Test Testson", "Password1");
  }

  @Test
  public void testConstructor() {
    Assertions.assertNotNull(registeredUser);
  }

  @Test
  public void testGetters() {
    Assertions.assertEquals(RegisteredUser.class, registeredUser.getClass());
    Assertions.assertEquals("testUser", registeredUser.getUsername());
    Assertions.assertEquals("Password1", registeredUser.getPassword());
    Assertions.assertEquals("Password1", registeredUser.getRepeatedPassword());
    Assertions.assertEquals("Test Testson", registeredUser.getFullName());
  }

  @Test
  public void testGenerateUser() {
    Assertions.assertEquals(User.class, registeredUser.generateUser().getClass());
  }
}
