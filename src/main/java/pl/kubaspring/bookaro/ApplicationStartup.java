package pl.kubaspring.bookaro;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kubaspring.bookaro.catalog.application.CatalogController;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationStartup(CatalogController catalogController,
                              @Value("${bookaro.catalog.query}") String title,
                              @Value("${bookaro.catalog.limit:3}") Long limit,
                              @Value("${bookaro.catalog.query.author}") String author) {
        this.catalogController = catalogController;
        this.title = title;
        this.limit = limit;
        this.author = author;

    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println();
        System.out.println("find by title  \"Pan\"");
        List<Book> books = catalogController.findByTitle(title);
        books.stream().limit(limit).forEach(System.out::println);

        System.out.println();
        System.out.println("find by author \"Henryk\"");
        List<Book> booksByAuthor = catalogController.findByAuthor(author);
        booksByAuthor.forEach(System.out::println);

    }
}
