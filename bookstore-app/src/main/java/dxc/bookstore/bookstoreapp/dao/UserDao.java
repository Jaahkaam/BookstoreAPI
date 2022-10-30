package dxc.bookstore.bookstoreapp.dao;

import dxc.bookstore.bookstoreapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}
