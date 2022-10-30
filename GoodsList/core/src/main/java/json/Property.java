package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** 
 * Specifies that this is the property subclass and connects it to the parent class product.
 */
@JsonTypeName("property")


public class Property extends Product {

  private String propertyType;
  private int yearBuilt;
  private int bedrooms;
  private int area;

  /**
   * A constructor for the class Property We are using Jackson Annotation to create executing rules
   * for jackson, JsonCreator specifies that this is a constructor. JsonProperty specifies which
   * fields should be set to what in the JSON-file
   */
  @JsonCreator
  public Property(
      @JsonProperty(value = "price") int price,
      @JsonProperty(value = "condition") String condition,
      @JsonProperty(value = "propertyType") String propertyType,
      @JsonProperty(value = "yearBuilt") int yearBuilt,
      @JsonProperty(value = "bedrooms") int bedrooms,
      @JsonProperty(value = "area") int area) {
    super(price, condition);
    this.propertyType = propertyType;
    this.yearBuilt = yearBuilt;
    this.bedrooms = bedrooms;
    this.area = area;
  }

  /**
   * Gets the type of property.
   *
   * @return String
   */
  public String getPropertyType() {
    return propertyType;
  }

  /**
   * Sets the type of property.
   *
   * @param propertyType type
   */
  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  /**
   * Gets the year the property was built.
   *
   * @return int
   */
  public int getYearBuilt() {
    return yearBuilt;
  }

  /**
   * Sets the year the property was built.
   *
   * @param yearBuilt year
   */
  public void setYearBuilt(int yearBuilt) {
    this.yearBuilt = yearBuilt;
  }

  /**
   * Gets the number og bedrooms in the property.
   *
   * @return int
   */
  public int getBedrooms() {
    return bedrooms;
  }

  /**
   * Sets the number of bedrooms in the property.
   *
   * @param bedrooms bedrooms
   */
  public void setBedrooms(int bedrooms) {
    this.bedrooms = bedrooms;
  }

  /**
   * Gets the area of the property.
   *
   * @return int
   */
  public int getArea() {
    return area;
  }

  /**
   * Sets the area of the property.
   *
   * @param area area
   */
  public void setArea(int area) {
    this.area = area;
  }
}
