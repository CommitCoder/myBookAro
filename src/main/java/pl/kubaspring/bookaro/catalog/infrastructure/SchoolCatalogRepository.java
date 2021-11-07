package pl.kubaspring.bookaro.catalog.infrastructure;

import org.springframework.stereotype.Repository;
import pl.kubaspring.bookaro.catalog.domain.Book;
import pl.kubaspring.bookaro.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SchoolCatalogRepository implements CatalogRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public SchoolCatalogRepository() {
        storage.put(1L, new Book(1L, "Potop", "Sienkiewicz",2022 ));
        storage.put(2L, new Book(2L, "Boy", "Cezary",2021 ));
        storage.put(3L, new Book(3L, "Boy 3 Schol", "Cezary Szefs",2022 ));
        storage.put(4L, new Book(4L, "Patos", "Steve",2020 ));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}
