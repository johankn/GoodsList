package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Specifies that this is the clothing subclass
 * and connects it to the parent class product 
 */

@JsonTypeName("clothing")

/**
 * A child class "Clothing" that inherits from the parent class "Product".
 * A Clothing object represents a piece of clothing that can be sold on our site.
 */

public class Clothing extends Product {


    private String type;
    private String color;
    private String brand;
    private String size;

    /**
    * A constructor for the class Clothing
    * We are using Jackson Annotation to create executing rules for jackson, 
    * JsonCreator specifies that this is a constructor.
    * JsonProperty specifies which fields should be set to what in the JSON-file
    */

    @JsonCreator
    public Clothing(
        @JsonProperty(value = "price") int price,
        @JsonProperty(value = "condition") String condition,
        @JsonProperty(value = "brand") String brand,
        @JsonProperty(value = "type") String type,
        @JsonProperty(value = "color") String color,
        @JsonProperty(value = "size") String size) {
        super(price, condition);
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.size = size;
    }
    
    
    /**
     * Gets the type of clothing
     * @return String
     */
    public String getType() {
        return type;
    }

    
    /**
     * Sets the type of clothing
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * Gets the color of the clothing
     * @return String
     */
    public String getColor() {
        return color;
    }

    
    /**
     * Sets the color of the clothing
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    
    /**
     * Gets the brand of the clothing
     * @return String
     */
    public String getBrand() {
        return brand;
    }

    
    /**
     * Sets the brand of the clothing
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    
    /**
     * Gets the size of the clothing
     * @return String
     */
    public String getSize() {
        return size;
    }

    
    /**
     * Sets the size of the clothing
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

}
