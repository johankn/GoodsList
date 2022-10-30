package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** 
 * Specifies that this is the electronics subclass and connects it to the parent class product .
 */
@JsonTypeName("electronics")



public class Electronics extends Product {

  private String brand;
  private String type;

  /**
   * A constructor for the class Electronics We are using Jackson Annotation to create executing
   * rules for jackson, JsonCreator specifies that this is a constructor. JsonProperty specifies
   * which fields should be set to what in the JSON-file
   */
  @JsonCreator
  public Electronics(
      @JsonProperty(value = "price") int price,
      @JsonProperty(value = "condition") String condition,
      @JsonProperty(value = "brand") String brand,
      @JsonProperty(value = "type") String type) {
    super(price, condition);
    this.brand = brand;
    this.type = type;
  }

  /**
   * Gets the brand of the electronic product.
   *
   * @return String
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the electronic product.
   *
   * @param brand brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the type of the electronic product.
   *
   * @return String
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the electronic product.
   *
   * @param type product
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return getBrand() + " " + getType();
  }
}
