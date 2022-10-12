package core;

public class Vehicles extends Product {

    private String brand;
    private String modelName;
    private int modelYear;

    public Vehicles(int price, String condition, String productTitle, String brand, String modelName, int modelYear) {
        super(price, condition, productTitle);
        this.brand = brand;
        this.modelName = modelName;
        this.modelYear = modelYear;
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

}
