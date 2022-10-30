package dxc.bookstore.bookstoreapp.util;

import dxc.bookstore.bookstoreapp.model.entity.Author;
import dxc.bookstore.bookstoreapp.model.request.AuthorRequest;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorTransformer {


    public static Author transformAuthorRequest(AuthorRequest authorRequest) {

        Author author = new Author();

        author.setName(authorRequest.getName());
        author.setBirthday(authorRequest.getBirthday());

        return author;
    }

    public static List<Author> transformAuthorRequests(List<AuthorRequest> authorRequests) {

        List<Author> authors = authorRequests.stream()
                .map(authorRequest -> new Author(authorRequest.getName(), authorRequest.getBirthday()))
                .collect(Collectors.toList());

        return authors;
    }
}
