package dxc.bookstore.bookstoreapp.service;

import dxc.bookstore.bookstoreapp.dao.AuthorDao;
import dxc.bookstore.bookstoreapp.dao.BookDao;
import dxc.bookstore.bookstoreapp.exception.BookNotFoundException;
import dxc.bookstore.bookstoreapp.exception.BookStoreValidationException;
import dxc.bookstore.bookstoreapp.model.entity.Author;
import dxc.bookstore.bookstoreapp.model.entity.Book;
import dxc.bookstore.bookstoreapp.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class BookService {

    private BookDao bookDao;
    private AuthorDao authorDao;

    @Autowired
    public BookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public Book addBook(Book book) {

        if (isIsbnExist(book)) {
            throw new BookStoreValidationException("ISBN already exists");
        }

        String errors = validateBookDetails(book);
        if (StringUtils.hasLength(errors)) {
            throw new BookStoreValidationException(errors);
        }

        populateAuthorList(book);

        String threadUser = ThreadContext.getThreadUser().getUsername();

        insertCreatedDate(book, threadUser);
        insertUpdatedDate(book, threadUser);

        return bookDao.save(book);
    }

    public List<Book> getBookList() {
        //Pageable pageable = PageRequest.of(0,3, Sort.by("title"));
        return bookDao.findAll();
    }

    private void insertCreatedDate(Book book, String userName) {
        book.setCreatedBy(userName);
        book.setCreatedDate(new Date());
    }

    private void insertUpdatedDate(Book book, String userName) {
        book.setUpdatedBy(userName);
        book.setUpdatedDate(new Date());
    }

    private void populateAuthorList(Book book) {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < book.getAuthors().size(); i++) {
            Optional<Author> optAuthor = authorDao.findById(book.getAuthors().get(i).getAuthorId());
            if (optAuthor.isPresent()) {
                authors.add(optAuthor.get());
            } else {
                throw new BookStoreValidationException("One of the authors does not exist");
            }
        }
        book.setAuthors(authors);
    }

    private boolean isIsbnExist(Book book) {
        Optional<Book> duplicateBook = bookDao.findById(book.getIsbn());
        if (duplicateBook.isPresent()) {
            return true;
        }
        return false;
    }

    private String validateBookDetails(Book book) {
        String errors = "";

        if (book.getPubYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "pubYear is in the future; ";
        }

        return errors;
    }

    public Book updateBook(Book book) {

        String errors = validateBookDetails(book);
        if (StringUtils.hasLength(errors)) {
            throw new BookStoreValidationException(errors);
        }

        Book bookToBeUpdated;
        Optional<Book> bookOpt = bookDao.findById(book.getIsbn());
        if (!bookOpt.isPresent()) {
            throw new BookNotFoundException(book.getIsbn() + " is not found in the records");
        } else {
            bookToBeUpdated = bookOpt.get();
        }

        bookToBeUpdated.setTitle(book.getTitle());
        bookToBeUpdated.setPubYear(book.getPubYear());
        bookToBeUpdated.setPrice(book.getPrice());
        bookToBeUpdated.setAuthors(book.getAuthors());
        bookToBeUpdated.setGenre(book.getGenre());
        populateAuthorList(bookToBeUpdated);
        insertUpdatedDate(bookToBeUpdated, ThreadContext.getThreadUser().getUsername());

        return bookDao.save(bookToBeUpdated);
    }

    public void deleteBook(String isbn) {
        Optional<Book> bookToBedeleted = bookDao.findById(isbn);
        if (!bookToBedeleted.isPresent()) {
            throw new BookNotFoundException("Book to be deleted was not found");
        }
        bookDao.delete(bookToBedeleted.get());
    }

    public List<Book> findByTitleAndAuthorId(String title, Integer authorId) {
        return bookDao.findBytitleAndAuthor(title, authorId);
    }

    public List<Book> addBooks(List<Book> books) {
        List<Book> addedBooks = new ArrayList<>();
        books.forEach(book -> addedBooks.add(addBook(book)) );
        return addedBooks;
    }

}
