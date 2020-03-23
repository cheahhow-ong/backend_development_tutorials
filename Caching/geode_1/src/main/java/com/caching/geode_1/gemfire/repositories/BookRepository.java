package com.caching.geode_1.gemfire.repositories;

import com.caching.geode_1.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
