package dxc.bookstore.bookstoreapp.service;

import dxc.bookstore.bookstoreapp.dao.UserDao;
import dxc.bookstore.bookstoreapp.exception.UserNotFoundException;
import dxc.bookstore.bookstoreapp.model.entity.User;
import dxc.bookstore.bookstoreapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User user) {
        insertCreatedDate(user);
        insertUpdatedDate(user);
        return userDao.save(user);
    }

    public List<User> addUsers(List<User> users) {
        List<User> userList = new ArrayList<>();
        users.forEach(user -> {
            userList.add(addUser(user));
        });
        return userList;
    }

    public List<User> getUserList() {
        return userDao.findAll();
    }

    private void insertCreatedDate(User user) {
        user.setCreatedDate(new Date());
        user.setCreatedBy(Constants.getSYSTEMUSER());
    }

    private void insertUpdatedDate(User user) {
        user.setUpdatedDate(new Date());
        user.setUpdatedBy(Constants.getSYSTEMUSER());
    }

    public User getUser(int userId) {
        Optional<User> optUser = userDao.findById(userId);
        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}

