package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import json.Ad;
import json.Books;
import json.Electronics;
import json.Product;
import json.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** This is a class for testing the ad sorting methods. */
public class AdSorterTest {

  private AdSorter adSorter;
  private List<Ad> ads;
  private Ad oldest;
  private Ad mid1;
  private Ad mid2;
  private Ad mid3;
  private Ad newest;
  private Product product1;
  private Product product2;
  private Product product3;
  private List<Ad> sortedAdsByBooks;

  /** A method that initializes objects before every test. */
  @BeforeEach
  public void setup() {
    //Create products:
    product1 = new Books(200, "Good", "JKR", "comic", 2010, 356);
    product2 = new Electronics(900, "Used", "LogiTech", "Keyboard");
    product3 = new Property(11500000, "New", "Apartment", 2022, 5, 183);

    oldest = new Ad("Good book 1", product1, "2022-10-01", "Nice book", "1", false);
    mid1 = new Ad("Good keyboard", product2, "2022-10-10", "Super keyboard", "2", false);
    mid2 = new Ad("Good book 2", product1, "2022-10-11", "Nice book", "3", false);
    mid3 = new Ad("Good apartment", product3, "2022-10-12", "Super apartment", "4", false);
    newest = new Ad("Good book 3", product1, "2022-10-30", "Nice book", "5", false);

    ads = new ArrayList<>();

    //Adding the add in reversed order (from oldest -> newest)
    ads.add(oldest);
    ads.add(mid1);
    ads.add(mid2);
    ads.add(mid3);
    ads.add(newest);

    adSorter = new AdSorter(ads);
    sortedAdsByBooks = adSorter.sortAds(ad -> ad.getProduct().getClass() == Books.class);
  }

  @Test
  public void testConstructor() {
    Assertions.assertNotNull(adSorter);
    AdSorter emptyAdSorter = new AdSorter();
    Assertions.assertEquals(0, emptyAdSorter.getAds().size());
  }

  @Test
  public void testGetAds() {
    Assertions.assertEquals(ads, adSorter.getAds());
  }

  @Test
  public void testSetAds() {
    ads = new ArrayList<Ad>();
    this.adSorter.setAds(ads);
    Assertions.assertEquals(ads, adSorter.getAds());
  }

  @Test
  public void testGetListOfAdsFromId() {
    List<String> idList = Arrays.asList("1", "3", "5");
    assertEquals(sortedAdsByBooks, adSorter.getListofAdsFromId(idList, ads));
  }

  @Test
  @DisplayName("Test for sorting by date")
  public void testSortAdsByDate() {
    List<Ad> sortedAdsByDate = adSorter.sortAdsByDate();
    assertEquals("2022-10-30", sortedAdsByDate.get(0).getDate());
    assertEquals("2022-10-12", sortedAdsByDate.get(1).getDate());
    assertEquals("2022-10-11", sortedAdsByDate.get(2).getDate());
    assertEquals("2022-10-10", sortedAdsByDate.get(3).getDate());
    assertEquals("2022-10-01", sortedAdsByDate.get(4).getDate());

    assertEquals(5, sortedAdsByDate.size());
  }

  @Test
  @DisplayName("Test for sorting by category")
  public void testSortAdsByCategory() {
    AdSorter adSort = new AdSorter(sortedAdsByBooks);
    List<Ad> newTest = adSort.sortAdsByDate();
    assertEquals("Good book 3", newTest.get(0).getAdTitle());
    assertEquals("Good book 2", newTest.get(1).getAdTitle());
    assertEquals("Good book 1", newTest.get(2).getAdTitle());

    assertEquals(3, sortedAdsByBooks.size());

    List<Ad> sortedAdsByProperty =
        adSorter.sortAds(ad -> ad.getProduct().getClass() == Property.class);
    assertEquals("Good apartment", sortedAdsByProperty.get(0).getAdTitle());

    assertEquals(1, sortedAdsByProperty.size());
  }

  @Test
  @DisplayName("Test for sorting by searchbar")
  public void testSortAdsBySearch() {
    String searchBar = "SuPeR";
    List<Ad> sortedAdsBySearch =
        adSorter.sortAds(
            ad ->
                ad.getDescription().toLowerCase().contains(searchBar.toLowerCase())
                    || ad.getAdTitle().toLowerCase().contains(searchBar.toLowerCase()));
    Assertions.assertTrue(
        sortedAdsBySearch.get(0).getDescription().toLowerCase().contains(searchBar.toLowerCase()));
    Assertions.assertTrue(
        sortedAdsBySearch.get(1).getDescription().toLowerCase().contains(searchBar.toLowerCase()));

    assertEquals(2, sortedAdsBySearch.size());
  }
}
