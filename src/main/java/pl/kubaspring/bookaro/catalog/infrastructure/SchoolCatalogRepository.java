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
public class SchoolCatalogRepository implements CatalogRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public SchoolCatalogRepository() {
        storage.put(1L, new Book(1L, "Pan Tadeusz", "Adam Mickiewicz",1811 ));
        storage.put(2L, new Book(2L, "Pan Wołodyjowski", "Henryk Sienkiewicz",1887 ));
        storage.put(3L, new Book(3L, "Potop", "Henryk Sienkiewicz",1886 ));
        storage.put(4L, new Book(4L, "Chłopi", "Władysław Reymont",1904 ));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

}
