package dxc.bookstore.bookstoreapp.service;

import dxc.bookstore.bookstoreapp.dao.AuthorDao;
import dxc.bookstore.bookstoreapp.exception.BookStoreValidationException;
import dxc.bookstore.bookstoreapp.model.entity.Author;
import dxc.bookstore.bookstoreapp.util.Constants;
import dxc.bookstore.bookstoreapp.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    public Author addAuthor(Author author) {

        String errors = validateAuthor(author);
        if (StringUtils.hasLength(errors)) {
            throw new BookStoreValidationException(errors);
        }

        author = insertCreatedDate(author);
        author = insertUpdatedDate(author);

        return authorDao.save(author);
    }

    public List<Author> getAuthorList() {
        return authorDao.findAll();
    }

    private Author insertCreatedDate(Author author) {
        author.setCreatedBy(Constants.getSYSTEMUSER());
        author.setCreatedDate(new Date());
        return author;
    }

    private Author insertUpdatedDate(Author author) {
        author.setUpdatedBy(Constants.getSYSTEMUSER());
        author.setUpdatedDate(new Date());
        return author;
    }

    private String validateAuthor(Author author) {
        String errors = "";

        if (!(author.getBirthday() != null && ValidationUtil.idDateStrValid(author.getBirthday()) && ValidationUtil.isDateBeforeToday(author.getBirthday()))) {
            errors += "Birthday is invalid; ";
        }

        return errors;
    }

    public List<Author> addAuthors(List<Author> authors) {
        List<Author> addedAuthors = new ArrayList<>();
        authors.forEach(author -> addedAuthors.add(addAuthor(author)));
        return addedAuthors;
    }
}
