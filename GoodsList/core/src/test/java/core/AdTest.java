package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class AdTest {

    private Ad ad;
    private User user;
    private Product product;
    private List<Integer> offers;

    @BeforeEach
    public void setUp() {
        this.user = new User("username", "password", "Ola Nordmann");
        this.product = new Product(10, "good");
        this.ad = new Ad("nice ad", product, user, "13.10.2022", "very nice product");
        this.offers = new ArrayList<>();
    }
    
    @Test
    public void TestConstructor() {
        assertEquals(Ad.class, ad.getClass());
        assertEquals("nice ad", ad.getAdTitle());
        assertEquals(product, ad.getProduct());
        assertEquals(user, ad.getOwner());
        assertEquals("13.10.2022", ad.getDate());
        assertEquals("very nice product", ad.getDescription());
        assertEquals(offers, ad.getOffers());
    }
    
    @Test
    public void TestSetter() {
        ad.setAdTitle("not nice ad");
        assertEquals("not nice ad", ad.getAdTitle());

        Product product2 = new Product(20, "bad");
        ad.setProduct(product2);
        assertEquals(product2, ad.getProduct());

        User user2 = new User("username2", "password2", "Ole Nordmann");
        ad.setOwner(user2);
        assertEquals(user2, ad.getOwner());

        ad.setDate("12.10.2022");
        assertEquals("12.10.2022", ad.getDate());

        ad.setDescription("not a nice product");
        assertEquals("not a nice product", ad.getDescription());

        ad.addOffer(200);
        this.offers.add(200);
        assertEquals(this.offers, this.ad.getOffers());

        List<Ad> testlist = new ArrayList<>();
        assertEquals(testlist, this.user.getActiveAds());
        this.ad.publishAd(user);
        testlist.add(ad);
        assertEquals(testlist, this.user.getActiveAds());

    }
}