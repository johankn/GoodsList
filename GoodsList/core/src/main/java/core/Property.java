package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("property")

public class Property extends Product {

    private String propertyType;
    private int yearBuilt;
    private int bedrooms;
    private int area; // bruksareal

    @JsonCreator
    public Property(
    @JsonProperty(value = "price") int price,
    @JsonProperty(value = "condition") String condition,
    @JsonProperty(value = "propertyType") String propertyType,
    @JsonProperty(value = "yearBuilt") int yearBuilt,
    @JsonProperty(value = "bedrooms") int bedrooms,
    @JsonProperty(value = "area") int area) {
        super(price, condition);
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
