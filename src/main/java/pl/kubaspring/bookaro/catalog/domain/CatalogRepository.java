package pl.kubaspring.bookaro.catalog.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// służy tylko do komunikacji z baza danych
//@Repository
public interface CatalogRepository {
    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Long id);

    void removeById(Long id);
}
