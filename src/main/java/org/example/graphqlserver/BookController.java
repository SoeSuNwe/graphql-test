package org.example.graphqlserver;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }

    @QueryMapping
    public Book filterBook(@Argument("filter") Filter filter) {
        return Book.filterBook(filter);
    }

    @QueryMapping
    public List<Book> filterBooks(@Argument("filters") List<Where> filters) {
        return Book.filterBooks(filters);
    }
}