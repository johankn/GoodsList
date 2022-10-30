package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Specifies that this is the books subclass and connects it to the parent class
 * product .
 */
@JsonTypeName("book")

public class Books extends Product {

  private int pages;
  private String author;
  private String genre;
  private int releaseYear;

  /**
   * A constructor for the class Books We are using Jackson Annotation to create
   * executing rules for
   * jackson, JsonCreator specifies that this is a constructor. JsonProperty
   * specifies which fields
   * should be set to what in the JSON-file
   */
  @JsonCreator
  public Books(
      @JsonProperty(value = "price") int price,
      @JsonProperty(value = "condition") String condition,
      @JsonProperty(value = "author") String author,
      @JsonProperty(value = "genre") String genre,
      @JsonProperty(value = "releaseYear") int releaseYear,
      @JsonProperty(value = "pages") int pages) {
    super(price, condition);
    this.pages = pages;
    this.author = author;
    this.genre = genre;
    this.releaseYear = releaseYear;
  }

  /**
   * Gets the pages of the book.
   *
   * @return int
   */
  public int getPages() {
    return pages;
  }

  /**
   * Sets amount of pages in the book.
   *
   * @param pages pages
   */
  public void setPages(int pages) {
    this.pages = pages;
  }

  /**
   * Gets the author of the book.
   *
   * @return String
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets the author of the book.
   *
   * @param author author
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Gets the genre of the book.
   *
   * @return String
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets the genre of the book.
   *
   * @param genre genre
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Gets the release year of the book.
   *
   * @return int
   */
  public int getReleaseYear() {
    return releaseYear;
  }

  /**
   * Sets the release year of the book.
   *
   * @param releaseYear year
   */
  public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
  }

  /**
   * A toString() for the Books class.
   * 
   * @return String
   */
  @Override
  public String toString() {
    return getAuthor() + " " + getGenre() + " " + getPages() + " " + getReleaseYear();
  }
}
