package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import json.Ad;

/** 
 * A class for sorting a list of ads in different ways.
 */
public class AdSorter {

  private List<Ad> ads;

  /** Constructor that has a list of ads as parameter. */
  public AdSorter(List<Ad> ads) {
    this.ads = ads;
  }
  
  /** Empty constructor. */
  public AdSorter() {
    this.ads = new ArrayList<Ad>();
  }

  /**
   * Sorts the list of ads by date and returs it.
   *
   * @return sortedAdsByDate 
   */
  public List<Ad> sortAdsByDate() {
    List<Ad> sortedAdsByDate = ads;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Collections.sort(
        sortedAdsByDate,
        (ad1, ad2) ->
            LocalDate.parse(ad2.getDate(), formatter)
                .compareTo(LocalDate.parse(ad1.getDate(), formatter)));

    return sortedAdsByDate;
  }

  /**
   * Get all ads.
   *
   * @return ads
   */
  public List<Ad> getAds() {
    return new ArrayList<Ad>(ads);
  }

  /**
   * Sets new ads.
   *
   * @param ads ads
   */
  public void setAds(List<Ad> ads) {
    this.ads = ads;
  }

  /**
   * Returns a list of ads sorted with a predicate.
   *
   * @param expression a predicate for sorting ads
   */
  public List<Ad> sortAds(Predicate<Ad> expression) {
    return this.ads.stream().filter(expression).collect(Collectors.toList());
  }

  /**
   * Returns a list of ads where the id of the ad matched the ids in idList.
   *
   * @param idList a list of ad ids
   * @param adList a list of ads
   */
  public List<Ad> getListofAdsFromId(List<String> idList, List<Ad> adList) {
    return adList.stream().filter(ad -> idList.contains(ad.getAdId())).collect(Collectors.toList());
  }
}
