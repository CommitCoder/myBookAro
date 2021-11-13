package pl.kubaspring.bookaro.catalog.application.port;

import lombok.Value;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface CatalogUseCase {
    List<Book> findByTitle(String title);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook(CreateBookCommand createBookCommand);

    void removeById(Long id);

    void updateBook();

    List<Book> findByAuthor(String author);

    @Value
     class CreateBookCommand{
         String title;
         String author;
         Integer year;
    }

}
