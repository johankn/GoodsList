package json;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Property.java
 */
public class PropertyTest {

  private Property property;

  @BeforeEach
  public void setUp() {
    this.property = new Property(300000, "good", "House", 2010, 4, 250);
  }

  @Test
  public void testConstructor() {
    assertEquals(Property.class, property.getClass()); // testing that the book has been "made"
    assertEquals(300000, property.getPrice());
    assertEquals("good", property.getCondition());
    assertEquals("House", property.getPropertyType());
    assertEquals(2010, property.getYearBuilt());
    assertEquals(4, property.getBedrooms());
    assertEquals(250, property.getArea());
  }

  @Test
  public void testSetters() {
    this.property.setPropertyType("apartment");
    assertEquals(
        "apartment", this.property.getPropertyType()); // testing that the change has been made

    this.property.setYearBuilt(2015);
    assertEquals(2015, this.property.getYearBuilt()); // testing that the change has been made

    this.property.setBedrooms(2);
    assertEquals(2, this.property.getBedrooms()); // testing that the change has been made

    this.property.setArea(100);
    assertEquals(100, this.property.getArea()); // testing that the change has been made
  }
}
