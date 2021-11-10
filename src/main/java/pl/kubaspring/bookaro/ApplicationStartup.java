package pl.kubaspring.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationStartup(CatalogUseCase catalog,
                              @Value("${bookaro.catalog.query}") String title,
                              @Value("${bookaro.catalog.limit:3}") Long limit,
                              @Value("${bookaro.catalog.query.author}") String author) {
        this.catalog = catalog;
        this.title = title;
        this.limit = limit;
        this.author = author;

    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("find by title  \"Pan\"");
        List<Book> books = catalog.findByTitle(title);
        books.stream().limit(limit).forEach(System.out::println);

        System.out.println();
        System.out.println("find by author \"Henryk\"");
        List<Book> booksByAuthor = catalog.findByAuthor(author);
        booksByAuthor.forEach(System.out::println);

    }
}
