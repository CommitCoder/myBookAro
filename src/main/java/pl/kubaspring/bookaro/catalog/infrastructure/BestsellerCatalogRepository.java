package pl.kubaspring.bookaro.catalog.infrastructure;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.kubaspring.bookaro.catalog.domain.Book;
import pl.kubaspring.bookaro.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BestsellerCatalogRepository implements CatalogRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public BestsellerCatalogRepository() {
        storage.put(1L, new Book(1L, "Harry Potter", "Rowling",2022 ));
        storage.put(2L, new Book(2L, "Boy", "Cezary",2021 ));
        storage.put(3L, new Book(3L, "Man", "Cezo",2022 ));
        storage.put(4L, new Book(4L, "Harry Potter 2", "Rowling",2020 ));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

}
