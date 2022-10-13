package core;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("electronics")

public class Electronics extends Product {

    private String brand;
    private String type;

    public Electronics(int price, String condition, String productTitle, String brand, String type) {
        super(price, condition, productTitle);
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
