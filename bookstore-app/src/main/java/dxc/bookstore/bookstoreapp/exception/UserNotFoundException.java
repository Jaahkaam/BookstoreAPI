package dxc.bookstore.bookstoreapp.exception;

public class UserNotFoundException extends BookStoreValidationException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
