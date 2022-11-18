package json;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdTest {

  private Ad ad;
  private User user;
  private Product product;

  /** A method that initializes ad, user and product before every test is executed. */
  @BeforeEach
  public void setUp() {
    this.product = new Product(10, "good");
    this.ad =
        new Ad("Selling this product", product, "13.10.2022", "very nice product", "3", false);
    this.user =
        new User("username", "password", "Ola Nordmann", new ArrayList<>(), new ArrayList<>());
  }

  @Test
  public void testConstructor() {
    assertEquals(Ad.class, ad.getClass());
    assertEquals("Selling this product", ad.getAdTitle());
    assertEquals(product, ad.getProduct());
    assertEquals("13.10.2022", ad.getDate());
    assertEquals("very nice product", ad.getDescription());
    assertEquals("3", ad.getAdId());
  }

  @Test
  public void testSetter() {
    ad.setAdTitle("not nice ad");
    assertEquals("not nice ad", ad.getAdTitle());
    Product product2 = new Product(20, "bad");
    ad.setProduct(product2);
    assertEquals(product2, ad.getProduct());
    ad.setDate("12.10.2022");
    assertEquals("12.10.2022", ad.getDate());
    ad.setDescription("not a nice product");
    assertEquals("not a nice product", ad.getDescription());
    List<String> testlist = new ArrayList<>();
    assertEquals(testlist, this.user.getMyAds());
    this.ad.publishAd(user);
    testlist.add(ad.getAdId());
    assertEquals(testlist, this.user.getMyAds());
    assertEquals(false, ad.getIsSold());
    ad.setIsSold(true);
    assertEquals(true, ad.getIsSold());
    assertEquals(ad.getAdTitle(), ad.toString());
    Ad ad2 = new Ad();
    ad2.setAdId("1901");
  }
}
