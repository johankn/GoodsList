package core;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Ad> activeAds = new ArrayList<Ad>();
    private List<Ad> inactiveAds = new ArrayList<Ad>();
    private String name;

    void addAdToList(Ad ad) {
        activeAds.add(ad);
    }

}
