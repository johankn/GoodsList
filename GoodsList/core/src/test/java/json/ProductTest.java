package json;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Product.java
 */
public class ProductTest {
    
  private Product product;

  /** Initializes product object before test. */
  @BeforeEach
  public void setUp() {
    this.product = new Product(1000, "Used");
  }

  @Test
  public void testCondition() {
    this.product.setCondition("New");
    assertEquals("New", this.product.getCondition());
  }

  @Test
  public void testPrice() {
    this.product.setPrice(10);
    assertEquals(10, this.product.getPrice());
  }
}
