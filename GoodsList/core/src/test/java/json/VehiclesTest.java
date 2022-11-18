package json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Vehicles.java
 */
public class VehiclesTest {

  private Vehicles vehicle;

  @BeforeEach
  public void setUp() {
    this.vehicle = new Vehicles(100, "good", "porsche", "911", 2019, "black");
  }

  @Test
  public void testConstructor() {
    Assertions.assertNotNull(vehicle);
    
  }

  @Test
  public void testGetter() {
    Assertions.assertEquals(
        Vehicles.class, vehicle.getClass()); // testing that the book has been "made"
    Assertions.assertEquals(100, vehicle.getPrice());
    Assertions.assertEquals("good", vehicle.getCondition());
    Assertions.assertEquals("porsche", vehicle.getBrand());
    Assertions.assertEquals("911", vehicle.getModelName());
    Assertions.assertEquals(2019, vehicle.getModelYear());
  }

  @Test
  public void testSetter() {
    this.vehicle.setBrand("audi");
    Assertions.assertEquals(
        "audi", this.vehicle.getBrand()); // testing that the change has been made

    this.vehicle.setModelName("r8");
    Assertions.assertEquals(
        "r8", this.vehicle.getModelName()); // testing that the change has been made

    this.vehicle.setModelYear(2021);
    Assertions.assertEquals(
        2021, this.vehicle.getModelYear()); // testing that the change has been made

    this.vehicle.setColor("blue");
    Assertions.assertEquals(
        "blue", this.vehicle.getColor()); // testing that the change has been made
  }
}
