package core;

import java.util.Date;

public class Property extends Product {

    private String propertyType;
    private int yearBuilt;
    private int bedrooms;
    private int area; // bruksareal

    public Property(int price, String condition, String productTitle, String propertyType, int yearBuilt, int bedrooms,
            int area) {
        super(price, condition, productTitle);
        this.propertyType = propertyType;
        this.yearBuilt = yearBuilt;
        this.bedrooms = bedrooms;
        this.area = area;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

}
