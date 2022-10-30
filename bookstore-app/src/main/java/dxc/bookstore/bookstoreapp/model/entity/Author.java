package dxc.bookstore.bookstoreapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_AUTHOR")
public class Author extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String name; // Assumption: There can be more than 1 author with the same name
    private String birthday; // Assumption: date will be in dd/MM/yyyy

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {}

    public Author(int authorId) {
        this.authorId = authorId;
    }

    public Author(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", books=" + books +
                '}';
    }
}
