package pl.kubaspring.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase.UpdateBookResponse;
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
        initData();
        findByTitle();
        findAndUpdate();
        findByAuthor();
    }

    private void initData() {
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Harry Potter", "Rowling",2019 ));
        catalog.addBook(new CatalogUseCase.CreateBookCommand( "Boy", "Cezary",2021 ));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Man", "Cezo",2022  ));
        catalog.addBook(new CatalogUseCase.CreateBookCommand( "Harry Potter 2", "Rowling",2020  ));
    }

    private void findByTitle() {
        System.out.println();
        System.out.println("find by title " + title);
        List<Book> books = catalog.findByTitle(title);
        books.stream().limit(limit).forEach(System.out::println);
    }

    private void findByAuthor() {
        System.out.println();
        System.out.println("find by author: " + author);
        List<Book> booksByAuthor = catalog.findByAuthor(author);
        booksByAuthor.forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println("Updating book ______");
        catalog.findOneByTitleAndAuthor("Harry Potter", "Rowling")
                .ifPresent(book -> {
                    UpdateBookCommand command = UpdateBookCommand.builder()
                        .id(book.getId())
                        .title("Harry Potter i Komnata tajemnic")
                        .build();
                    UpdateBookResponse response = catalog.updateBook(command);
                    System.out.println("Updating book result:" + response.isSuccess());
                });

    }

}
