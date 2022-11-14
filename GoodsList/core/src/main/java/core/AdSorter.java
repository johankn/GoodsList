package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import json.Ad;

/**
 * class for sorting list of ads in different ways.
 */
public class AdSorter {

  /*
   * This class sorts ads by different preferences.
   */

  private List<Ad> ads;

  public AdSorter(List<Ad> ads) {
    this.ads = ads;
  }

  public AdSorter() {}

  /**
   * sorts the list and returs the sortedlist by date.
   *
   * @return ads
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

  
  // /**
  //  * sorts the list and returs the sortedlist by date.
  //  *
  //  * @param function funtion to sort
  //  * @param listOfAds list of ads
  //  * @return ads
  //  */
  // public List<String> getAdAttributeInFile(List<Ad> listOfAds, Function<Ad, String> function) {
  //   return listOfAds.stream().map(function).toList();
  // }

  /**
   * get all ads.
   *
   * @return ads
   */
  public List<Ad> getAds() {
    return new ArrayList<Ad>(ads);
  }

  /**
   * sets new ads. 
   *
   * @param ads ads
   */
  public void setAds(List<Ad> ads) {
    this.ads = ads;
  }

  public List<Ad> sortAds(Predicate<Ad> expression) {
    return this.ads.stream().filter(expression).collect(Collectors.toList());
  }

  public List<Ad> getListofAdsFromId(List<String> idList, List<Ad> adList) {
    return adList.stream().filter(ad -> idList.contains(ad.getAdID())).collect(Collectors.toList());
  }
}
