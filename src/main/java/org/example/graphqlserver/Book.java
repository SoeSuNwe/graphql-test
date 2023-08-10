package org.example.graphqlserver;

import java.util.Arrays;
import java.util.List;

public class Book {

    private String id;
    private String name;
    private int pageCount;
    private String authorId;

    public Book(String id, String name, int pageCount, String authorId) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.authorId = authorId;
    }

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
            new Book("book-2", "Moby Dick", 635, "author-2"),
            new Book("book-3", "Interview with the vampire", 371, "author-3")
    );

    public static Book getById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public static Book filterBook(Filter filter) {

        System.out.println("Filter value");
        System.out.println(filter.getAttributeId() + "\t" + filter.getOperator() + "\t" + filter.getAttributeValue());

        return books.stream().filter(book -> book.getId().equals("1")).findFirst().orElse(null);
    }

    public static List<Book> filterBooks(List<Where> whereList) {
        System.out.println("**************************filterBooks****** size " + whereList.size());
        for (var i = 0; i < whereList.size(); i++) {
            System.out.println("**************************filterBooks****** index " + i);
            recursiveWhere(whereList.get(i), i);
        }
        return books;
    }


    public static void filterOutput(Filter filter, int i) {
        System.out.println("filter=>" + i + "=>" + filter.getAttributeId() + " " + filter.getOperator()
                + " " + filter.getAttributeValue() + "\t");
    }

    public static void recursiveWhere(Where where, int i) {
        if (where.getFilter() != null) {
            filterOutput(where.getFilter(), i);
        }
        if (where.getAnd() != null) {
            for (Where w : where.getAnd()) {
                recursiveWhere(w, i);
            }
        }
        if (where.getOr() != null) {
            for (Where w : where.getOr()) {
                recursiveWhere(w, i);
            }
        }
        if (where.getNot() != null) {
            recursiveWhere(where.getNot(), i);
        }
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }
}