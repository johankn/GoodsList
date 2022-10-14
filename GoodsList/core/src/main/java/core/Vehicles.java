package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("vehicles")

public class Vehicles extends Product {

    private String brand;
    private String modelName;
    private int modelYear;
    private String color;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }
 
}
