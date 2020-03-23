package com.caching.geode_1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Indexed;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.time.ZonedDateTime;

@Data
@Region("booksRegion")
@NoArgsConstructor
public class Book {
    @Id
    private String bookId;

    @Indexed
    private String authorId;

    @Indexed
    private String publisherId;

    private String genre;
    private BookType bookType;
    private ZonedDateTime published;

    @PersistenceConstructor
    public Book(String bookId, String genre, String authorId, String publisherId,
                BookType bookType, ZonedDateTime published) {
        this.bookId = bookId;
        this.genre = genre;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.bookType = bookType;
        this.published = published;
    }
}
