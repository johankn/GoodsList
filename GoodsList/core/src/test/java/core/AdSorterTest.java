package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import json.Ad;
import json.Books;
import json.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class AdSorterTest {

    private AdSorter adSorter;
    private List<Ad> ads;
    private Ad oldest;
    private Ad mid;
    private Ad newest;
    private Product product1;
    

    @BeforeEach
    public void setup(){
        //Make product:
        product1 = new Books(200, "Good", "JKR", "comic", 2010,356);

        //Using the same product becuase the class as of for now oly sorts on date.
        oldest = new Ad("Good book 1", product1, "2022-10-01", "Nice book", "1", false);
        mid = new Ad("Good book 2", product1, "2022-10-10", "Nice book", "2", false);
        newest = new Ad("Good book 3", product1, "2022-10-16", "Nice book", "3", false);

        ads = new ArrayList<>();

        //Adding the add in reversed order (from oldest -> newest)
        ads.add(oldest);
        ads.add(mid);
        ads.add(newest);

        adSorter = new AdSorter(ads);
    }

    @Test
    @DisplayName("Test for sorting on date")
    public void testSortAdsByDate(){
        List<Ad> sortedAdsByDate = adSorter.sortAdsByDate();
        assertEquals("2022-10-16", sortedAdsByDate.get(0).getDate());
        assertEquals("2022-10-10", sortedAdsByDate.get(1).getDate());
        assertEquals("2022-10-01", sortedAdsByDate.get(2).getDate());
        
        //Checking that the order is the same when calling method one more time
        sortedAdsByDate =  adSorter.sortAdsByDate();
        assertEquals("2022-10-16", sortedAdsByDate.get(0).getDate());
        assertEquals("2022-10-10", sortedAdsByDate.get(1).getDate());
        assertEquals("2022-10-01", sortedAdsByDate.get(2).getDate());

        assertEquals(3, sortedAdsByDate.size());
    }
    
}
