package dxc.bookstore.bookstoreapp.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_BOOK")
public class Book extends BaseModel {

    @Id
    private String isbn; // Assumption: isbn is unique enough so no duplicate books exists (Hence, same details but different ISBN). Qty is always 1
    private String title;
    private int pubYear;
    private double price;
    private String genre;

    @ManyToMany
    @JoinTable(
            name = "TB_BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors; // Assumption: Authors has to exists first before they can be added

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", pubYear=" + pubYear +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                '}';
    }
}
