package core;

import org.junit.jupiter.api.Test;

import json.Ad;
import json.Product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class AdTest {

    private Ad ad;
    private User user;
    private Product product;

    @BeforeEach
    public void setUp() {
        this.product = new Product(10, "good");
        this.ad = new Ad("Selling this product", product, "13.10.2022", "very nice product");
        this.user = new User("username", "password", "Ola Nordmann", new ArrayList<>());
    }
    
    @Test
    public void TestConstructor() {
        assertEquals(Ad.class, ad.getClass());
        assertEquals("Selling this product", ad.getAdTitle());
        assertEquals(product, ad.getProduct());
        assertEquals("13.10.2022", ad.getDate());
        assertEquals("very nice product", ad.getDescription());
    }
    
    @Test
    public void TestSetter() {
        ad.setAdTitle("not nice ad");
        assertEquals("not nice ad", ad.getAdTitle());

        Product product2 = new Product(20, "bad");
        ad.setProduct(product2);
        assertEquals(product2, ad.getProduct());

        ad.setDate("12.10.2022");
        assertEquals("12.10.2022", ad.getDate());

        ad.setDescription("not a nice product");
        assertEquals("not a nice product", ad.getDescription());

        List<Ad> testlist = new ArrayList<>();
        assertEquals(testlist, this.user.getActiveAds());
        this.ad.publishAd(user);
        testlist.add(ad);
        assertEquals(testlist, this.user.getActiveAds());

    }
}