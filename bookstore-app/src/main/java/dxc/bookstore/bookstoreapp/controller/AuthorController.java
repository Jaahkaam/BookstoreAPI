package dxc.bookstore.bookstoreapp.controller;

import dxc.bookstore.bookstoreapp.model.request.ApiRequest;
import dxc.bookstore.bookstoreapp.model.response.ApiResponse;
import dxc.bookstore.bookstoreapp.model.entity.Author;
import dxc.bookstore.bookstoreapp.model.request.AuthorRequest;
import dxc.bookstore.bookstoreapp.service.AuthorService;
import dxc.bookstore.bookstoreapp.util.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/setup/author")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/addAuthor")
    public @ResponseBody
    ApiResponse<Author> addAuthor(@RequestBody ApiRequest<AuthorRequest> authorApiRequest) {
        System.out.println("addAuthor Post API called");
        return new ApiResponse<>(authorService.addAuthor(AuthorTransformer.transformAuthorRequest(authorApiRequest.getData())), HttpStatus.CREATED);
    }

    @PostMapping("/addAuthors")
    public @ResponseBody
    ApiResponse<List<Author>> addAuthors(@RequestBody ApiRequest<List<AuthorRequest>> authorApiRequests) {
        System.out.println("addAuthors Post API called");
        return new ApiResponse<>(authorService.addAuthors(AuthorTransformer.transformAuthorRequests(authorApiRequests.getData())), HttpStatus.CREATED);
    }

    @GetMapping("/getAllAuthors")
    public @ResponseBody
    ApiResponse<List<Author>> getAuthorList() {
        System.out.println("getAuthorList Get API called");
        return new ApiResponse<>(authorService.getAuthorList(), HttpStatus.OK);
    }
}
