package core;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ad {

    private Product product;
    private String date;
    private String description;
    
    @JsonIgnore
    private ArrayList<Integer> offers;
    /* public Ad(Product product, String date, String description, int id) {
        this.product = product;
    private String adTitle;

    public Ad(String adTitle, Product product, User owner, String date, String description) {
        this.product = product;
        this.adTitle = adTitle;
        this.owner = owner;
        this.date = date;
        this.description = description;
        this.offers = new ArrayList<>();
    } */

    @JsonCreator
    public Ad(
        @JsonProperty(value = "product") Product product,
        @JsonProperty(value = "date")String date,
        @JsonProperty(value = "description")String description
        ) {
        this.product = product;
        this.date = date;
        this.description = description;
        this.offers = new ArrayList<>();
    }

    public Ad() {

    }

    public String getAdTitle() {
        return adTitle;
    }
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    /* public User getOwner() {
        return owner;
    } */
    /* public void setOwner(User owner) {
        this.owner = owner;
    } */
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public ArrayList<Integer> getOffers() {
        return this.offers;
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
    
    public void publishAd(User user) {
        user.addAdToList(this);
    }

}