package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for AdValidator.java
 */
public class AdValidatorTest {

  private AdValidator adValidator;
  private String price;
  private String title;
  private String description;
  private String year;
  private String type;
  private String brand;
  private String size;
  private String genre;
  private String author;
  private String pages;
  private String bedrooms;
  private String area;

  /**
   * Methos to initzialize fields for objects used in tests.
  */
  @BeforeEach
  public void setUp() {
    this.adValidator = new AdValidator();
    this.price = "1000";
    this.title = "Title";
    this.description = "Description";
    this.year = "2021";
    this.type = "Type";
    this.brand = "Brand";
    this.size = "Size";
    this.genre = "Genre";
    this.author = "Author";
    this.pages = "1000";
    this.bedrooms = "9";
    this.area = "100";
  }

  @Test
  @DisplayName("test validateElectronics method")
  public void testValidateElectronics() {
    Assertions.assertTrue(adValidator.validateElectronics(title, description, price, brand, type));

    this.title = "";
    this.description = "";
    this.price = "";
    this.brand = "";
    this.type = "";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateElectronics(title, description, price, brand, type));

    this.title = "";
    this.description = "";
    this.price = "000";
    this.brand = "Miele";
    this.type = "Fridge";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateElectronics(title, description, price, brand, type));

    this.title = "";
    this.description = "";
    this.price = "1000";
    this.brand = "";
    this.type = "Fridge";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateElectronics(title, description, price, brand, type));
  }

  @Test
  @DisplayName("test validateClothing method")
  public void testValidateClothing() {
    Assertions.assertTrue(
        adValidator.validateClothing(title, description, price, brand, type, size));

    this.title = "";
    this.description = "";
    this.price = "";
    this.brand = "";
    this.type = "";
    this.size = "";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateClothing(title, description, price, brand, type, size));

    this.title = "";
    this.description = "";
    this.price = "halla";
    this.brand = "Adidas";
    this.type = "Jumper";
    this.size = "Medium";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateClothing(title, description, price, brand, type, size));

    this.title = "";
    this.description = "";
    this.price = "-200";
    this.brand = "";
    this.type = "Jumper";
    this.size = "Large";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateClothing(title, description, price, brand, type, size));
  }

  @Test
  @DisplayName("test validateProperty method")
  public void testValidateProperty() {
    Assertions.assertTrue(
        adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.title = "";
    this.description = "";
    this.price = "";
    this.type = "";
    this.year = "";
    this.bedrooms = "";
    this.area = "";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.title = "title";
    this.description = "";
    this.price = "000";
    this.type = "House";
    this.year = "2022";
    this.bedrooms = "11";
    this.area = "20";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.title = "title";
    this.description = "description";
    this.price = "200";
    this.type = "Apartment";
    this.year = "1000";
    this.bedrooms = "8";
    this.area = "gg";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.bedrooms = "gg";
    this.area = "100";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.bedrooms = "11";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));

    this.bedrooms = "4";
    this.price = "gg";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateProperty(title, description, price, type, year, bedrooms, area));
  }

  @Test
  @DisplayName("test validateVehicles method")
  public void testValidateVehicles() {
    Assertions.assertTrue(
        adValidator.validateVehicles(title, description, price, brand, type, year));

    this.title = "title";
    this.description = "description";
    this.price = "100";
    this.brand = "ford";
    this.type = "focus";
    this.year = "2030";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateVehicles(title, description, price, brand, type, year));

    this.title = "title";
    this.description = "description";
    this.price = "100";
    this.brand = "ford";
    this.type = "focus";
    this.year = "gg";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateVehicles(title, description, price, brand, type, year));

    this.title = "title";
    this.description = "";
    this.price = "-200";
    this.brand = "";
    this.type = "focus";
    this.year = "2000";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateClothing(title, description, price, brand, type, size));
  }

  @Test
  @DisplayName("test validateBooks method")
  public void testValidateBooks() {
    Assertions.assertTrue(
        adValidator.validateBooks(title, description, price, author, genre, year, area));

    this.title = "";
    this.description = "";
    this.price = "";
    this.author = "";
    this.genre = "";
    this.year = "";
    this.area = "";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateBooks(title, description, price, author, genre, year, pages));

    this.title = "title";
    this.description = "Description";
    this.price = "100";
    this.author = "yooo";
    this.genre = "Comedy";
    this.year = "11";
    this.pages = "31000";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateBooks(title, description, price, author, genre, year, pages));

    this.title = "title";
    this.description = "description";
    this.price = "200";
    this.author = "JK Rowling";
    this.genre = "Roman";
    this.year = "2000";
    this.pages = "gg";
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> adValidator.validateBooks(title, description, price, author, genre, year, pages));
  }
}
