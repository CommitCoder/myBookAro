package pl.kubaspring.bookaro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.kubaspring.bookaro.catalog.domain.Book;
import pl.kubaspring.bookaro.order.application.port.PlaceOrderUseCase;
import pl.kubaspring.bookaro.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.kubaspring.bookaro.order.application.port.PlaceOrderUseCase.PlaceOrderResponse;
import pl.kubaspring.bookaro.order.application.port.QueryOrderUseCase;
import pl.kubaspring.bookaro.order.domain.OrderItem;
import pl.kubaspring.bookaro.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final CatalogUseCase catalog;
    private final PlaceOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationStartup(CatalogUseCase catalog,
                              PlaceOrderUseCase placeOrder,
                              QueryOrderUseCase queryOrder,
                              @Value("${bookaro.catalog.query}") String title,
                              @Value("${bookaro.catalog.limit}") Long limit,
                              @Value("${bookaro.catalog.query.author}") String author) {
        this.catalog = catalog;
        this.placeOrder = placeOrder;
        this.queryOrder = queryOrder;
        this.title = title;
        this.limit = limit;
        this.author = author;

    }

    @Override
    public void run(String... args) throws Exception {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void searchCatalog() {
        findByTitle();
        findAndUpdate();
        findByAuthor();
    }

    private void initData() {
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Tadeusz", "Adam Mickiewicz", 1834, new BigDecimal("19.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Ogniem i Mieczem", "Henryk Sienkiewicz", 1884, new BigDecimal("29.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Chłopi", "Władysław Reymont", 1904, new BigDecimal("11.90")));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Pan Wołodyjowski", "Henryk Sienkiewicz", 1899, new BigDecimal("14.90")));
    }

    private void findByTitle() {
        System.out.println();
        System.out.println("find by title _____" + title);
        List<Book> books = catalog.findByTitle(title);
        books.stream().limit(limit).forEach(System.out::println);
    }

    private void findAndUpdate() {
        System.out.println();
        System.out.println("Updating book ______");
        catalog.findOneByTitleAndAuthor("Pan", "Adam")
                .ifPresent(book -> {
                    UpdateBookCommand command = UpdateBookCommand.builder()
                            .id(book.getId())
                            .title("Pan Tadeusz czyli Ostatni zajazd na Litwie")
                            .build();
                    UpdateBookResponse response = catalog.updateBook(command);
                    System.out.println("Updating book result:" + response.isSuccess());
                });
    }

    private void findByAuthor() {
        System.out.println();
        System.out.println("find by author _____ " + author);
        List<Book> booksByAuthor = catalog.findByAuthor(author);
        booksByAuthor.forEach(System.out::println);
    }

    private void placeOrder() {
        Book panTadeusz = catalog.findOneByTitle("Pan Tadeusz").orElseThrow(() -> new IllegalStateException("Cannot find a book"));
        Book chlopi = catalog.findOneByTitle("Chłopi").orElseThrow(() -> new IllegalStateException("Cannot find a book"));

        Recipient recipient = Recipient
                .builder()
                .name("Jan Kowalski")
                .phone("123-456-678")
                .street("Grzybowska 100")
                .city("Poznań")
                .zipCode("07-777")
                .email("kowal@example.com")
                .build();

        PlaceOrderCommand command = PlaceOrderCommand
                .builder()
                .recipient(recipient)
                .item(new OrderItem(panTadeusz, 16))
                .item(new OrderItem(chlopi, 7))
                .build();

        PlaceOrderResponse response = placeOrder.placeOrder(command);
        System.out.println();
        System.out.println("Created ORDER with id: " + response.getOrderId());

        queryOrder.findAll()
                .forEach(order -> {
                    System.out.println("GOT ORDER WITH TOTAL PRICE: " + order.totalPrice());
                    System.out.println(" DETAILS " + order);
                });
    }

}
