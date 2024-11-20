package com.example.bookHaven.repository.specification;

import com.example.bookHaven.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? null : criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, criteriaBuilder) ->
                genre == null ? null : criteriaBuilder.equal(root.get("genre"), genre);
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                author == null ? null : criteriaBuilder.equal(root.get("author"), author);
    }
}
