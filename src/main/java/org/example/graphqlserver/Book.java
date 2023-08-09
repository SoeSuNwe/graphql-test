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

    public static List<Book> filterBooks(List<Where> filters) {
        System.out.println("************************");
        for (var i = 0; i < filters.size(); i++) {
            recursiveFilter(filters, i);
            System.out.println("************************");
        }
        return books;
    }

    public static void recursiveFilter(List<Where> whereList, int head) {
        if (whereList.size() > head) {
            System.out.println("filter=>[" + head + "] \t" + whereList.get(head).getFilter().getAttributeId() + " " + whereList.get(head).getFilter().getOperator()
                    + " " + whereList.get(head).getFilter().getAttributeValue() + "\t");

            if ((whereList.get(head).getAnd() != null) && (whereList.get(head).getAnd().size() > 0)) {
                for (var i = 0; i < whereList.get(head).getAnd().size(); i++) {
                    System.out.printf("And[" + head + "]");
                    recursiveFilter(whereList.get(head).getAnd(), i);
                }
            }
            if ((whereList.get(head).getOr() != null) && whereList.get(head).getOr().size() > 0) {
                for (var i = 0; i < whereList.get(head).getOr().size(); i++) {
                    System.out.printf(" OR[" + head + "]");
                    recursiveFilter(whereList.get(head).getOr(), i);
                }
            }
            if (whereList.get(head).getNot() != null) {
                System.out.println("Not[" + head + "]filter=>[" + head + "]\t" + whereList.get(head).getNot().getFilter().getAttributeId() + " " + whereList.get(head).getNot().getFilter().getOperator()
                        + " " + whereList.get(head).getNot().getFilter().getAttributeValue() + "\t");
                if ((whereList.get(head).getNot().getAnd() != null) && whereList.get(head).getNot().getAnd().size() > 0) {
                    for (var i = 0; i < whereList.get(head).getNot().getAnd().size(); i++) {
                        System.out.printf("Not[" + head + "] And=>");
                        recursiveFilter(whereList.get(head).getNot().getAnd(), i);
                    }
                }
                if ((whereList.get(head).getNot().getOr() != null) && whereList.get(head).getNot().getOr().size() > 0) {
                    for (var i = 0; i < whereList.get(head).getNot().getOr().size(); i++) {
                        System.out.printf("Not[" + head + "] OR=>");
                        recursiveFilter(whereList.get(head).getNot().getOr(), i);
                    }
                }
                if (whereList.get(head).getNot().getNot() != null) {
                    System.out.println("Not[" + head + "]filter=>[" + head + "]\t" + whereList.get(head).getNot().getNot().getFilter().getAttributeId() +
                            " " + whereList.get(head).getNot().getNot().getFilter().getOperator()
                            + " " + whereList.get(head).getNot().getNot().getFilter().getAttributeValue() + "\t");
                    if ((whereList.get(head).getNot().getNot().getAnd() != null) && whereList.get(head).getNot().getNot().getAnd().size() > 0) {
                        for (var i = 0; i < whereList.get(head).getNot().getNot().getAnd().size(); i++) {
                            System.out.printf("Not[" + head + "] And=>");
                            recursiveFilter(whereList.get(head).getNot().getNot().getAnd(), i);
                        }
                    }
                    if ((whereList.get(head).getNot().getNot().getOr() != null) && whereList.get(head).getNot().getNot().getOr().size() > 0) {
                        for (var i = 0; i < whereList.get(head).getNot().getNot().getOr().size(); i++) {
                            System.out.printf("Not[" + head + "] OR=>");
                            recursiveFilter(whereList.get(head).getNot().getNot().getOr(), i);
                        }
                    }
                }
            }

        } else {
            recursiveFilter(whereList, head + 1);
        }
    }


    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }
}