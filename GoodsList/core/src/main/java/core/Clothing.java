package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("clothing")

public class Clothing extends Product {


    private String type;
    private String color;
    private String brand;
    private String size;

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
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
