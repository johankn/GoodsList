package core;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private List<Ad> activeAds = new ArrayList<Ad>();
    //private List<Ad> inactiveAds = new ArrayList<Ad>();
    private String fullname;
    private String username;
    private String password;

    public User(String username, String password, String fullname) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }
    public User(
        @JsonProperty(value = "username") String username,
        @JsonProperty(value = "password") String password,
        @JsonProperty(value = "fullname") String fullname,
        @JsonProperty(value = "activeAds") List<Ad> activeAds
    ) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.activeAds = activeAds;
    }



    public void addAdToList(Ad ad) {
        activeAds.add(ad);
    }

    public List<Ad> getActiveAds() {
        return activeAds;
    }

    /* public List<Ad> getInactiveAds() {
        return inactiveAds;
    } */

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setActiveAds(List<Ad> activeAds) {
        this.activeAds = activeAds;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

    

}
