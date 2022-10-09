package core;

import java.util.ArrayList;
import java.util.Date;

public class Ad {
    private Product productName;
    private User owner;
    private Date date;
    private String location;
    private ArrayList<Integer> offers;
    private String description;
    
    public Product getProductName() {
        return productName;
    }
    public void setProductName(Product productName) {
        this.productName = productName;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public ArrayList<Integer> getOffers() {
        return offers;
    }
    public void setOffers(ArrayList<Integer> offers) {
        this.offers = offers;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}