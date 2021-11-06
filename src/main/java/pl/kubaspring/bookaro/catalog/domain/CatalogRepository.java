package pl.kubaspring.bookaro.catalog.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

// służy tylko do komunikacji z baza danych
//@Repository
public interface CatalogRepository {
    List<Book> findAll();
}
