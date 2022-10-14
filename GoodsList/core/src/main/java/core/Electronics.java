package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("electronics")

public class Electronics extends Product {

    private String brand;
    private String type;

    @JsonCreator
    public Electronics(@JsonProperty(value = "price")int price, 
    @JsonProperty(value = "condition") String condition,
    @JsonProperty(value = "brand") String brand,
    @JsonProperty(value = "type") String type) {
        super(price, condition);
        this.brand = brand;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
