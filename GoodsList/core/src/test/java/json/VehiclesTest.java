package json;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehiclesTest {

  private Vehicles vehicle;

  @BeforeEach
  public void setUp() {
    this.vehicle = new Vehicles(100, "good", "porsche", "911", 2019, "black");
  }

  @Test
  public void testConstructor() {
    assertEquals(Vehicles.class, vehicle.getClass()); // testing that the book has been "made"
    assertEquals(100, vehicle.getPrice());
    assertEquals("good", vehicle.getCondition());
    assertEquals("porsche", vehicle.getBrand());
    assertEquals("911", vehicle.getModelName());
    assertEquals(2019, vehicle.getModelYear());
  }

  @Test
  public void testSetters() {
    this.vehicle.setBrand("audi");
    assertEquals("audi", this.vehicle.getBrand()); // testing that the change has been made

    this.vehicle.setModelName("r8");
    assertEquals("r8", this.vehicle.getModelName()); // testing that the change has been made

    this.vehicle.setModelYear(2021);
    assertEquals(2021, this.vehicle.getModelYear()); // testing that the change has been made

    this.vehicle.setColor("blue");
    assertEquals("blue", this.vehicle.getColor()); // testing that the change has been made
  }
}
