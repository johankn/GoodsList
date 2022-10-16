package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class "Ad" that represents an ad that can be posted to our site.
 */

public class Ad {

    private Product product;
    private String date;
    private String description;
    private String adTitle;

    /**
    * A constructor for the class Ad
    * We are using Jackson Annotation to create executing rules for jackson, 
    * JsonCreator specifies that this is a constructor.
    * JsonProperty specifies which fields should be set to what in the JSON-file
    */

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

    
    /** 
     * Gets the title of the ad
     * @return String
     */
    public String getAdTitle() {
        return adTitle;
    }

    
    /** 
     * Sets the title of the ad
     * @param adTitle
     */
    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    
    /** 
     * Gets the type of product in the ad
     * @return Product
     */
    public Product getProduct() {
        return product;
    }

    
    /** 
     * Sets the type of product in the ad
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    
    /** 
     * Gets the date the ad was posted
     * @return String
     */
    public String getDate() {
        return date;
    }

    
    /** 
     * Sets the date the ad was posted
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    
    /** 
     * Gets the descriiption of the ad
     * @return String
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * Sets the descriiption of the ad
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /** 
     * A method that adds this ad to a users list of its active ads
     * @param user
     */
    public void publishAd(User user) {
        user.addAdToList(this);
    }


}