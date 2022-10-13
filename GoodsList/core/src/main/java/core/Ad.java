package core;

import java.util.ArrayList;

public class Ad {
    private Product product;
    private User owner;
    private String date;
    private ArrayList<Integer> offers;
    private String description;
    private int id;

    public Ad(Product product, User owner, String date, String description, int id) {
        this.product = product;
        this.owner = owner;
        this.date = date;
        this.description = description;
        this.id = id;
        this.offers = new ArrayList<>();
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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