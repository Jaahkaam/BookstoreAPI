package dxc.bookstore.bookstoreapp.exception;

public class BookNotFoundException extends BookStoreValidationException {

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
