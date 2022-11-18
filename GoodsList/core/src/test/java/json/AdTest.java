package json;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * Test class for Ad.java
 */
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
    Assertions.assertNotNull(ad);
    Ad emptyConstructor = new Ad();
    Assertions.assertNull(emptyConstructor.getAdId());
  }

  @Test
  public void testGetter() {
    Assertions.assertEquals(Ad.class, ad.getClass());
    Assertions.assertEquals("Selling this product", ad.getAdTitle());
    Assertions.assertEquals(product, ad.getProduct());
    Assertions.assertEquals("13.10.2022", ad.getDate());
    Assertions.assertEquals("very nice product", ad.getDescription());
    Assertions.assertEquals("3", ad.getAdId());
    Assertions.assertEquals(false, ad.getIsSold());
  }

  @Test
  public void testSetter() {
    ad.setAdTitle("not nice ad");
    Assertions.assertEquals("not nice ad", ad.getAdTitle());

    Product product2 = new Product(20, "bad");
    ad.setProduct(product2);
    Assertions.assertEquals(product2, ad.getProduct());

    ad.setDate("12.10.2022");
    Assertions.assertEquals("12.10.2022", ad.getDate());

    ad.setDescription("not a nice product");
    Assertions.assertEquals("not a nice product", ad.getDescription());

    List<String> testlist = new ArrayList<>();
    Assertions.assertEquals(testlist, this.user.getMyAds());
  
    this.ad.publishAd(user);
    testlist.add(ad.getAdId());
    Assertions.assertEquals(testlist, this.user.getMyAds());

    ad.setIsSold(true);
    Assertions.assertEquals(true, ad.getIsSold());

    Assertions.assertEquals(ad.getAdTitle(), ad.toString());
    
    Ad ad2 = new Ad();
    ad2.setAdId("1901");
    Assertions.assertEquals("1901", ad2.getAdId());
  }
}
