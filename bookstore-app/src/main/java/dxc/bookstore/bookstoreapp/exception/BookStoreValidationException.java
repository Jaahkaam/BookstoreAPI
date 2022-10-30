package dxc.bookstore.bookstoreapp.exception;

public class BookStoreValidationException extends RuntimeException {

    public BookStoreValidationException(String message) {
        super(message);
    }

    public BookStoreValidationException(String message, Exception exception) {
        super(message, exception);
    }
}
