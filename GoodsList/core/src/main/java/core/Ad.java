package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ad {

    private Product product;
    private String date;
    private String description;
    private String adTitle;

    @JsonCreator
    public Ad(
            @JsonProperty(value = "adTitle") String adTitle,
            @JsonProperty(value = "product") Product product,
            @JsonProperty(value = "date") String date,
            @JsonProperty(value = "description") String description) {
        this.product = product;
        this.adTitle = adTitle;
        this.date = date;
        this.description = description;
    }

    /* 
     * Empty constructor to make a preview of an ad
     * @param none
     */
    public Ad(){

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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