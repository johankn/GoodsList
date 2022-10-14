package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "@type")
  
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

    @JsonCreator
    public Product(
            @JsonProperty(value = "price") int price,
            @JsonProperty(value = "condition") String condition) {
        this.price = price;
        this.condition = condition;
    }

    /*
     * public Product(int price, String condition, String productTitle) {
     * 
     * this.price = price;
     * this.condition = condition;
     * this.productTitle = productTitle;
     * }
     */

    public String getCondition() {
        return this.condition;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
