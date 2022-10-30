package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** 
 * Specifies that this is the vehicles subclass and connects it to the parent class product.
 */
@JsonTypeName("vehicles")



public class Vehicles extends Product {

  private String brand;
  private String modelName;
  private int modelYear;
  private String color;

  /**
   * A constructor for the class Vehicles We are using Jackson Annotation to create executing rules
   * for jackson, JsonCreator specifies that this is a constructor. JsonProperty specifies which
   * fields should be set to what in the JSON-file
   */
  @JsonCreator
  public Vehicles(
      @JsonProperty(value = "price") int price,
      @JsonProperty(value = "condition") String condition,
      @JsonProperty(value = "brand") String brand,
      @JsonProperty(value = "modelName") String modelName,
      @JsonProperty(value = "modelYear") int modelYear,
      @JsonProperty(value = "color") String color) {
    super(price, condition);
    this.brand = brand;
    this.modelName = modelName;
    this.modelYear = modelYear;
    this.color = color;
  }

  /**
   * Gets the brand of the vehicle.
   *
   * @return String
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the vehicle.
   *
   * @param brand brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the model name of the vehicle.
   *
   * @return String
   */
  public String getModelName() {
    return modelName;
  }

  /**
   * Sets the model name of the vehicle.
   *
   * @param modelName modelname
   */
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  /**
   * Gets the model year of the vehicle.
   *
   * @return int
   */
  public int getModelYear() {
    return modelYear;
  }

  /**
   * Sets the model year of the vehicle.
   *
   * @param modelYear year
   */
  public void setModelYear(int modelYear) {
    this.modelYear = modelYear;
  }

  /**
   * Gets the color of the vehicle.
   *
   * @return String
   */
  public String getColor() {
    return this.color;
  }

  /**
   * Sets the color of the vehicle.
   *
   * @param color color
   */
  public void setColor(String color) {
    this.color = color;
  }

  
  /**
   * A toString() for the Vehicles class.
   * @return String
   */
  @Override
  public String toString() {
    return getBrand() + " " + getModelName() + " " + getColor() + " " + getModelYear();
  }
}
