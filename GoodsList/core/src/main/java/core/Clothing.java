package core;

public class Clothing extends Product {


    private String type;
    private String color;
    private String brand;
    private String size;

    public Clothing(int price, String condition, String productTitle, String brand, String type, String color, String size) {
        super(price, condition, productTitle);
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
