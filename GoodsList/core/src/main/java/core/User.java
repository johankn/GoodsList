package core;

import java.util.ArrayList;
import java.util.List;

public class User {
    List<Ad> activeAds = new ArrayList<Ad>();
    List<Ad> inactiveAds = new ArrayList<Ad>();
    String name;

    void addAdToList(Ad ad) {
        activeAds.add(ad);
    }

}
