package json;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private Ad ad;
  private User user;
  private Product product;

  /** Set up method for initializing the objects. */
  @BeforeEach
  public void setUp() {
    this.product = new Product(1500, "new");
    this.ad = new Ad("PC for sale", product, "13.11.2022", "Brand new PC", "5", false);
    this.user =
      new User("itpMan", "Qwerty123", "Kari Larsen", new ArrayList<>(), new ArrayList<>());
  }

  @Test
  public void testConstructor() {
    assertEquals(User.class, user.getClass());
    assertEquals("Kari Larsen", user.getFullname());
    assertEquals("itpMan", user.getUsername());
    assertEquals("Qwerty123", user.getPassword());
    assertEquals(0, user.getMyAds().size());
    assertEquals(0, user.getBoughtAds().size());
  }

  @Test
  public void testBuyAd() {
    assertEquals(0, user.getBoughtAds().size());
    this.user.buyAd(ad.getAdId());
    System.out.println(user.getBoughtAds());
    assertEquals(Arrays.asList("5"), this.user.getBoughtAds());
    this.user.setFullname("John Smith");
    assertEquals("John Smith", this.user.getFullname());
    List<String> myAds = Arrays.asList("1", "2", "3");
    this.user.setMyAds(myAds);
    assertEquals(myAds, this.user.getMyAds());
    List<String> boughtAds = Arrays.asList("4");
    this.user.setBoughtAds(boughtAds);
    assertEquals(boughtAds, this.user.getBoughtAds());
    assertThrows(IllegalArgumentException.class, () -> 
        user.buyAd("1"));
  }
}
