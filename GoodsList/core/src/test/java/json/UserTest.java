package json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private Ad ad;
  private User user;
  private Product product;
  private List<String> myAds = new ArrayList<String>();

  /** Set up method for initializing the objects. */
  @BeforeEach
  public void setUp() {
    this.myAds.add("1");
    this.product = new Product(1500, "new");
    this.ad = new Ad("PC for sale", product, "13.11.2022", "Brand new PC", "2", false);
    this.user =
      new User("itpMan", "Qwerty123", "Kari Larsen", myAds, new ArrayList<>());
  }

  @Test
  public void testConstructor() {
    Assertions.assertNotNull(user);
  }

  @Test
  public void testGetter() {
    Assertions.assertEquals(User.class, user.getClass());
    Assertions.assertEquals("Kari Larsen", user.getFullname());
    Assertions.assertEquals("itpMan", user.getUsername());
    Assertions.assertEquals("Qwerty123", user.getPassword());
    Assertions.assertEquals(1, user.getMyAds().size());
    Assertions.assertEquals(0, user.getBoughtAds().size());
  }

  @Test
  public void testSetter() {
    this.user.setFullname("John Smith");
    Assertions.assertEquals("John Smith", this.user.getFullname());

    this.user.setUsername("johnnyBoy");
    Assertions.assertEquals("johnnyBoy", this.user.getUsername());

    this.user.setPassword("TopSecret1");
    Assertions.assertEquals("TopSecret1", this.user.getPassword());

    this.user.setMyAds(Arrays.asList("4, 5"));
    Assertions.assertEquals(Arrays.asList("4, 5"), user.getMyAds());

    this.user.setBoughtAds(Arrays.asList("6, 7"));
    Assertions.assertEquals(Arrays.asList("6, 7"), user.getBoughtAds());
  }

  @Test
  public void testAddAdToListAndBuyAd() {
    this.user.addAdToList(ad.getAdId());
    Assertions.assertEquals(2, user.getMyAds().size());

    Assertions.assertThrows(IllegalArgumentException.class, () -> this.user.buyAd("2"));

    Ad wantThisAd = new Ad("Ferrari", product, "10.11.2022", "Ferrari 2015 model", "3", false);
    this.user.buyAd(wantThisAd.getAdId());
    Assertions.assertEquals(1, user.getBoughtAds().size());
  }
}
