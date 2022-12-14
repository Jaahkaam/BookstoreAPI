package dxc.bookstore.bookstoreapp.dao;

import dxc.bookstore.bookstoreapp.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

}
