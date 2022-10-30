package dxc.bookstore.bookstoreapp.exception.handler;

import dxc.bookstore.bookstoreapp.exception.BookNotFoundException;
import dxc.bookstore.bookstoreapp.exception.BookStoreValidationException;
import dxc.bookstore.bookstoreapp.exception.BookTransformationException;
import dxc.bookstore.bookstoreapp.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public ApiResponse handleBookNotFoundException(BookNotFoundException exception) {
        System.out.println("Exception: " + exception.getMessage());
        return new ApiResponse(null, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookStoreValidationException.class)
    @ResponseBody
    public ApiResponse handleValidationException(BookStoreValidationException exception) {
        System.out.println("Exception: " + exception.getMessage());
        return new ApiResponse(null, exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BookTransformationException.class)
    @ResponseBody
    public ApiResponse handleBookTransformationException(BookTransformationException exception) {
        System.out.println("Exception: " + exception.getMessage());
        return new ApiResponse(null, exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse handleAllOtherException(Exception exception) {
        System.out.println("Exception: " + exception.getMessage());
        return new ApiResponse(null, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
