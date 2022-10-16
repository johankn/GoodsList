package core;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    /*
     * This class represents a user in the app.
     */

    private List<Ad> activeAds;
    private String fullname;
    private String username;
    private String password;

    /**
     * A constructor for the class User
     * We are using Jackson Annotation to create executing rules for jackson,
     * JsonCreator specifies that this is a constructor.
     * JsonProperty specifies which fields should be set to what in the JSON-file
     */

    @JsonCreator
    public User(
            @JsonProperty(value = "username") String username,
            @JsonProperty(value = "password") String password,
            @JsonProperty(value = "fullname") String fullname,
            @JsonProperty(value = "activeAds") List<Ad> activeAds) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.activeAds = activeAds;
    }

    /**
     * Add an ad to the adList activeAds.
     * 
     * @param ad
     */
    public void addAdToList(Ad ad) {
        activeAds.add(ad);
    }

    /**
     * @return List<Ad>
     */
    public List<Ad> getActiveAds() {
        return new ArrayList<Ad>(activeAds);
    }

    /**
     * @return String
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param activeAds
     */
    public void setActiveAds(List<Ad> activeAds) {
        this.activeAds = activeAds;
    }

    /**
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
