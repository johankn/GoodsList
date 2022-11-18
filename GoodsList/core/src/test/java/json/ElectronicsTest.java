package json;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Electronics.java
 */
public class ElectronicsTest {

  private Electronics electronics;

  @BeforeEach
  public void setUp() {
    this.electronics = new Electronics(300, "good", "Apple", "phone");
  }

  @Test
  public void testConstructor() {
    assertEquals(
        Electronics.class, electronics.getClass()); // testing that the book has been "made"
    assertEquals(300, electronics.getPrice());
    assertEquals("good", electronics.getCondition());
    assertEquals("Apple", electronics.getBrand());
    assertEquals("phone", electronics.getType());
  }

  @Test
  public void testSetters() {
    this.electronics.setBrand("Samsung");
    assertEquals("Samsung", this.electronics.getBrand()); // testing that the change has been made

    this.electronics.setType("computer");
    assertEquals("computer", this.electronics.getType()); // testing that the change has been made
  }
}
