package dxc.bookstore.bookstoreapp.util;

import dxc.bookstore.bookstoreapp.exception.BookTransformationException;
import dxc.bookstore.bookstoreapp.model.entity.Author;
import dxc.bookstore.bookstoreapp.model.entity.Book;
import dxc.bookstore.bookstoreapp.model.request.BookRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BookTransformer {

    public static Book transformBookRequest(BookRequest bookRequest) {

        Book book = new Book();

        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());

        try {
            book.setPubYear(Integer.parseInt(bookRequest.getPubYear()));
            book.setPrice(Double.parseDouble(bookRequest.getPrice()));
        } catch (NumberFormatException ne) {
            throw new BookTransformationException("Book request is invalid");
        }

        book.setGenre(bookRequest.getGenre());
        List<Author> authors = bookRequest.getAuthorIds().stream()
                .map(authorId -> new Author(authorId))
                .collect(Collectors.toList());
        book.setAuthors(authors);

        System.out.println("Book Transformed: " + book);

        return book;
    }

    public static List<Book> transformBookRequests(List<BookRequest> bookRequests) {

        return bookRequests.stream().map(
                bookRequest -> transformBookRequest(bookRequest)
        ).collect(Collectors.toList());

    }
}
