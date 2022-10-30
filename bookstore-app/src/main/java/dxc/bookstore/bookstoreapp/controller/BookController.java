package dxc.bookstore.bookstoreapp.controller;

import dxc.bookstore.bookstoreapp.model.entity.Book;
import dxc.bookstore.bookstoreapp.model.request.ApiRequest;
import dxc.bookstore.bookstoreapp.model.request.BookRequest;
import dxc.bookstore.bookstoreapp.model.request.DeleteBookRequest;
import dxc.bookstore.bookstoreapp.model.request.SearchBookRequest;
import dxc.bookstore.bookstoreapp.model.response.ApiResponse;
import dxc.bookstore.bookstoreapp.service.BookService;
import dxc.bookstore.bookstoreapp.util.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/book")
@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public @ResponseBody
    ApiResponse<Book> addBook(@RequestBody ApiRequest<BookRequest> bookApiRequest) {
        System.out.println("addBook Post API called");
        System.out.println("BookApiRequest" + bookApiRequest);
        return new ApiResponse<>(bookService.addBook(BookTransformer.transformBookRequest(bookApiRequest.getData())), HttpStatus.CREATED);
    }

    @PostMapping("/addBooks")
    public @ResponseBody
    ApiResponse<List<Book>> addBooks(@RequestBody ApiRequest<List<BookRequest>> bookApiRequests) {
        System.out.println("addBook Post API called");
        System.out.println("BookApiRequest" + bookApiRequests);
        return new ApiResponse<>(bookService.addBooks(BookTransformer.transformBookRequests(bookApiRequests.getData())), HttpStatus.CREATED);
    }

    @GetMapping("/getAllBooks")
    public @ResponseBody ApiResponse<List<Book>> getBookList() {
        System.out.println("getBookList Get API called");
        return new ApiResponse<>(bookService.getBookList(), HttpStatus.OK);
    }

    @PostMapping("/updateBook")
    public @ResponseBody ApiResponse<Book> updateBook(@RequestBody ApiRequest<BookRequest> bookApiRequest) {
        System.out.println("updateBook Post API called");
        System.out.println("BookApiRequest" + bookApiRequest);
        return new ApiResponse<>(bookService.updateBook(BookTransformer.transformBookRequest(bookApiRequest.getData())), HttpStatus.OK);

    }

    @PostMapping("/secured/deleteBook")
    public @ResponseBody ApiResponse<Book> deleteBook(@RequestBody ApiRequest<DeleteBookRequest> deleteBookRequest) {
        bookService.deleteBook(deleteBookRequest.getData().getIsbn());
        return new ApiResponse<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/findBook")
    public @ResponseBody ApiResponse<List<Book>> findBooks (@RequestBody ApiRequest<SearchBookRequest> searchBookRequest) {
        System.out.println("FindBook Post API called");
        String title = searchBookRequest.getData().getTitle();
        Integer authorId = searchBookRequest.getData().getAuthorId();
        System.out.println("Search Title: " + title);
        System.out.println("Search authorId: " + authorId);
        return new ApiResponse<>(bookService.findByTitleAndAuthorId(title, authorId), HttpStatus.OK);
    }


}
