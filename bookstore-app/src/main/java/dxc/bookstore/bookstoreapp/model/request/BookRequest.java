package dxc.bookstore.bookstoreapp.model.request;

import java.util.List;

public class BookRequest {

    private String isbn;
    private String title;
    private List<Integer> authorIds;
    private String pubYear;
    private String price;
    private String genre;

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

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public String getPubYear() {
        return pubYear;
    }

    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authorIds=" + authorIds +
                ", pubYear='" + pubYear + '\'' +
                ", price='" + price + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
