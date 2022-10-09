package core;

public class Books extends Product {

    private int pages;
    private String author;
    private String genre;
    private int releaseYear;

    public Books(int pages, String author, String genre, int releaseYear, int price, String condition,
            String productTitle) {
        super(price, condition, productTitle);
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
