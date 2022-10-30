package json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Declaring some jsoninfo.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")


@JsonSubTypes({
    @Type(value = Books.class, name = "book"),
    @Type(value = Clothing.class, name = "clothing"),
    @Type(value = Electronics.class, name = "electronics"),
    @Type(value = Property.class, name = "property"),
    @Type(value = Vehicles.class, name = "vehicles"),
})


public class Product {

  private int price;
  private String condition;

  /**
   * A constructor for the superclass Product.
   *
   * @param price , Every product has a price value associated
   * @param condition , Every product has a set condition
   */
  public Product(int price, String condition) {
    this.price = price;
    this.condition = condition;
  }

  /**
   * Gets the condition of the product.
   */
  public String getCondition() {
    return this.condition;
  }
  

  /**
   * Gets the price of the product.
   *
   * @return int
   */
  public int getPrice() {
    return this.price;
  }

  /**
   * Sets the price of the product.
   *
   * @param price price
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * Sets the condition of the product to be new or not.
   *
   * @param condition condition
   */
  public void setCondition(String condition) {
    this.condition = condition;
  }
}
