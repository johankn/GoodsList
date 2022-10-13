package core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ElectronicsTest {

    private Electronics electronics;
    

    @BeforeEach
    public void setUp() {
        this.electronics = new Electronics(300, "good", "x", "Apple", "phone");
    }

    @Test
    public void TestConstructor() {
        assertEquals(Electronics.class, electronics.getClass()); //testing that the book has been "made"
        assertEquals(300, electronics.getPrice());
        assertEquals("good", electronics.getCondition());
        assertEquals("Apple", electronics.getBrand());
        assertEquals("phone", electronics.getType());
    }

    @Test
    public void TestSetters() {
        this.electronics.setBrand("Samsung");
        assertEquals("Samsung", this.electronics.getBrand()); //testing that the change has been made

        this.electronics.setType("computer");
        assertEquals("computer", this.electronics.getType()); //testing that the change has been made
    }

}
