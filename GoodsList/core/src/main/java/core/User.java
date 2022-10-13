package core;

import java.util.ArrayList;
import java.util.List;

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

    

}
