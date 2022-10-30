package core;

import java.util.Calendar;

/** 
* A class "AdValidator" that checks if the written input is valid .
*/
public class AdValidator {

  /**
   * A method that checks if the inputted string only contains whole numbers, returns true if it
   * does, returns false if it does not.
   *
   * @param field field
   * @return boolean
   */
  private boolean validatePriceField(String field) {
    if (!(field.matches("[1-9][0-9]*"))) {
      throw new IllegalArgumentException("Price field must only contain whole numbers");
    }
    return validateBlankField(field);
  }

  /**
   * A method that checks if a field is blank, returns true if it is not blank, will throw an
   * exception if it is blank.
   *
   * @param field field
   * @return boolean
   */
  private boolean validateBlankField(String field) {
    if (field.isBlank()) {
      throw new IllegalArgumentException("You have to fill out all of the input fields");
    }
    return true;
  }

  /**
   * A method that checks if the input only contains numbers. Will also put a maximum number of
   * bedroos to 10. If the input is illegal, it will throw an exception. If the input is legal it
   * return true
   *
   * @param bedrooms int
   * @return boolean
   */
  private boolean validateBedrooms(String bedrooms) {
    if (!(bedrooms.matches("[1-9][0-9]*"))) {
      throw new IllegalArgumentException("Bedrooms field must only contain numbers");
    }
    if (Integer.parseInt(bedrooms) > 10) {
      throw new IllegalArgumentException("You can't have more than 10 bedrooms");
    }

    return validateBlankField(bedrooms);
  }

  /**
   * A method that checks if the inputted year is valid, throws an exception if it is not. 
   *
   * @param year year
   * @return boolean
   */
  private boolean validateYear(String year) {
    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    if (!(year.matches("[1-9][0-9]*"))) {
      throw new IllegalArgumentException("Year field must only contain numbers");
    }

    if (Integer.parseInt(year) > thisYear) {
      throw new IllegalArgumentException("Year can't be in the future");
    }

    return validateBlankField(year);
  }

  /**
   * A method that checks if the area input is legal, throws an exception if it is not.
   *
   * @param area area
   * @return boolean
   */
  private boolean validateArea(String area) {
    if (!(area.matches("[1-9][0-9]*"))) {
      throw new IllegalArgumentException("Area field must only contain numbers");
    }
    // begrensing på hvor stort huset kan være?
    return validateBlankField(area);
  }

  /**
   * A method that checks if the pages input is legal, throws an exception if it is not.
   *
   * @param pages pages
   * @return boolean
   */
  private boolean validatePages(String pages) {
    if (!(pages.matches("[1-9][0-9]*"))) {
      throw new IllegalArgumentException("Pages field must only contain numbers");
    }
    if (Integer.parseInt(pages) > 30000) {
      throw new IllegalArgumentException("Can't be more than 30 000 pages");
    }
    return validateBlankField(pages);
  }

  /**
   * A method that checks if all the fields in the Electronics input is legal, throws an exception
   * if it is not.
   *
   * @param title title
   * @param description description
   * @param price price
   * @param brand brand
   * @param type type
   * @return boolean
   */
  public boolean validateElectronics(
      String title, String description, String price, String brand, String type) {
    return validateBlankField(title)
        && validateBlankField(description)
        && validateBlankField(brand)
        && validateBlankField(type)
        && validatePriceField(price);
  }

  /**
   * A method that checks if all the fields in the Clothing input is legal, throws an exception if
   * it is not.
   *
   * @param title
   * @param description
   * @param price
   * @param brand
   * @param type
   * @param size
   * @return boolean
   */
  public boolean validateClothing(
      String title, String description, String price, String brand, String type, String size) {
    return validateBlankField(title)
        && validateBlankField(description)
        && validateBlankField(brand)
        && validateBlankField(type)
        && validateBlankField(size)
        && validatePriceField(price);
  }

  /**
   * A method that checks if all the fields in the Property input is legal, throws an exception if
   * it is not
   *
   * @param title
   * @param description
   * @param price
   * @param propertyType
   * @param yearBuilt
   * @param bedrooms
   * @param area
   * @return boolean
   */
  public boolean validateProperty(
      String title,
      String description,
      String price,
      String propertyType,
      String yearBuilt,
      String bedrooms,
      String area) {
    return validateBlankField(title)
        && validateBlankField(description)
        && validateBlankField(propertyType)
        && validateArea(area)
        && validateYear(yearBuilt)
        && validateBedrooms(bedrooms)
        && validatePriceField(price);
  }

  /**
   * A method that checks if all the fields in the Vehicles input is legal, throws an exception if
   * it is not
   *
   * @param title
   * @param description
   * @param price
   * @param brand
   * @param modelName
   * @param modelYear
   * @return boolean
   */
  public boolean validateVehicles(
      String title,
      String description,
      String price,
      String brand,
      String modelName,
      String modelYear) {
    return validateBlankField(title)
        && validateBlankField(description)
        && validateBlankField(brand)
        && validateBlankField(modelName)
        && validateYear(modelYear)
        && validatePriceField(price);
  }

  /**
   * A method that checks if all the fields in the Books input is legal, throws an exception if it
   * is not
   *
   * @param title
   * @param description
   * @param price
   * @param author
   * @param genre
   * @param releaseYear
   * @param pages
   * @return boolean
   */
  public boolean validateBooks(
      String title,
      String description,
      String price,
      String author,
      String genre,
      String releaseYear,
      String pages) {
    return validateBlankField(title)
        && validateBlankField(description)
        && validateBlankField(author)
        && validateBlankField(genre)
        && validateYear(releaseYear)
        && validatePages(pages)
        && validatePriceField(price);
  }
}
