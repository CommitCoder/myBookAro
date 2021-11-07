package pl.kubaspring.bookaro;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kubaspring.bookaro.catalog.application.CatalogController;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogController catalogController;

    // metoda ta uruchamia się w momencie gdy już cała apka wstanie (wstanie kontekst Spirnga, wstanie apka )
    @Override
    public void run(String... args) throws Exception {
        List<Book> books = catalogController.findByTitle("Boy");
        books.forEach(System.out::println);
    }
}
