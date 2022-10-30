package dxc.bookstore.bookstoreapp.dao;

import dxc.bookstore.bookstoreapp.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book, String> {


    //@Query("SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE (:title IS NULL OR b.title = :title) AND (:authorid IS NULL OR a.authorId = :authorid)")
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE (:title IS NULL OR (lower(b.title) LIKE lower(concat('%',:title,'%')))) AND (:authorid IS NULL OR a.authorId = :authorid)")
    List<Book> findBytitleAndAuthor(@Param("title") String title, @Param("authorid") Integer authorId);

}
