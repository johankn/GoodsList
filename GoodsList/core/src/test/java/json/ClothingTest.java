package json;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Clothing.java
 */
public class ClothingTest {
  private Clothing jumper;

  @BeforeEach
  public void setUp() {
    this.jumper = new Clothing(300, "good", "Polo", "Hoodie", "blue", "medium");
  }

  @Test
  public void testConstructor() {
    assertEquals(Clothing.class, jumper.getClass()); // testing that the book has been "made"
    assertEquals(300, jumper.getPrice());
    assertEquals("good", jumper.getCondition());
    assertEquals("Polo", jumper.getBrand());
    assertEquals("Hoodie", jumper.getType());
    assertEquals("blue", jumper.getColor());
    assertEquals("medium", jumper.getSize());
  }

  @Test
  public void testSetters() {
    this.jumper.setBrand("Nike");
    assertEquals("Nike", this.jumper.getBrand()); // testing that the change has been made

    this.jumper.setType("Half-zip");
    assertEquals("Half-zip", this.jumper.getType()); // testing that the change has been made

    this.jumper.setColor("grey");
    assertEquals("grey", this.jumper.getColor()); // testing that the change has been made

    this.jumper.setSize("large");
    assertEquals("large", this.jumper.getSize()); // testing that the change has been made
  }
}
