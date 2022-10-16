package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdSorter {

    /*
     * This class sorts ads by different preferences.
     */

    private List<Ad> ads;

    public AdSorter(List<Ad> ads) {
        this.ads = ads;
    }

    /**
     * sorts the list and returs the sortedlist by date.
     * 
     * @return List<Ad>
     */
    public List<Ad> sortAdsByDate() {
        List<Ad> sortedAdsByDate = ads;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Collections.sort(sortedAdsByDate, (ad1, ad2) -> LocalDate.parse(ad2.getDate(), formatter)
                .compareTo(LocalDate.parse(ad1.getDate(), formatter)));

        return sortedAdsByDate;

    }

    public List<String> getAdAttributeInFile(List<Ad> listOfAds, Function<Ad,String> function){
        return listOfAds.stream().map(function).toList();
    }

    /**
     * @return List<Ad>
     */
    public List<Ad> getAds() {
        return ads;

    }

    /**
     * @param ads
     */
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

}
