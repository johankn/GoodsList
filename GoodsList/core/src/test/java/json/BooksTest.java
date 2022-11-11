package json;

import org.junit.jupiter.api.Test;

import json.Books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BooksTest {

    private Books book;
    

    @BeforeEach
    public void setUp() {
        this.book = new Books(300, "good", "J.K Rowling", "Fiction", 2009, 350);
    }

    @Test
    public void TestConstructor() {
        assertEquals(Books.class, book.getClass()); //testing that the book has been "made"
        assertEquals(300, book.getPrice());
        assertEquals("good", book.getCondition());
        assertEquals("J.K Rowling", book.getAuthor());
        assertEquals("Fiction", book.getGenre());
        assertEquals(2009, book.getReleaseYear());
        assertEquals(350, book.getPages());
    }

    @Test
    public void TestSetters() {
        this.book.setAuthor("Not J.K Rowling");
        assertEquals("Not J.K Rowling", this.book.getAuthor()); //testing that the change has been made

        this.book.setPages(200);
        assertEquals(200, this.book.getPages()); //testing that the change has been made

        this.book.setGenre("Based on a true story");
        assertEquals("Based on a true story", this.book.getGenre()); //testing that the change has been made

        this.book.setReleaseYear(2010);
        assertEquals(2010, this.book.getReleaseYear()); //testing that the change has been made
    }
}
