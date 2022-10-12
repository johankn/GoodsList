package core;

import java.util.ArrayList;
import java.util.Date;

public class Ad {
    private Product product;
    private User owner;
    private Date date;
    private String location;
    private ArrayList<Integer> offers;
    private String description;
    private int id;

    public Ad(Product product, User owner, Date date, String location, String description) {
        this.product = product;
        this.owner = owner;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
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
    public void addOffer(Integer offer) {
        offers.add(offer);
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private void publishAd(User user) {
        user.addAdToList(this);
    }

}