package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 * Sets this class as a superclass and tells JSON what to look for to understand the inheritance hierarchy.
 */

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "@type")

/**
 * Naming and categorizing the subclasses,
 * connecting them to a type so that the parent class can separate them.
 */

@JsonSubTypes({ 
    @Type(value = Books.class, name = "book"),  
    @Type(value = Clothing.class, name = "clothing"),  
    @Type(value = Electronics.class, name = "electronics"),  
    @Type(value = Property.class, name = "property"),  
    @Type(value = Vehicles.class, name = "vehicles"),  
  })

/**
 * A superclass Product that holds the common attributes and methods for all its subclasses
 */
public class Product {

    private int price;
    private String condition;

    public Product(int price, String condition) {
        this.price = price;
        this.condition = condition;
    }

    /**
     * Gets the condition of the product
     * @param price
     */
    public String getCondition() {
        return this.condition;
    }

    /**
     * Gets the price of the product
     * @return int
     */
    public int getPrice() {
        return this.price;
    }

    
    /**
     * Sets the price of the product
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets the condition of the product to be new or not
     * @param condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

}
