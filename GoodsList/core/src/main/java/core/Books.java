package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("book")

public class Books extends Product {

    private int pages;
    private String author;
    private String genre;
    private int releaseYear;

    @JsonCreator
    public Books(
        @JsonProperty(value = "price") int price,
        @JsonProperty(value = "condition") String condition,
        @JsonProperty(value = "author") String author,
        @JsonProperty(value = "genre") String genre,
        @JsonProperty(value = "releaseYear") int releaseYear,
        @JsonProperty(value = "pages") int pages
    ){
        super(price, condition);
        this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.releaseYear = releaseYear;

    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
