package pl.kubaspring.bookaro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookaroOnlineStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BookaroOnlineStoreApplication.class, args);
	}


	private final CatalogService catalogService;

	// automatycznie odpalany przez Springa w momencie startu
	public BookaroOnlineStoreApplication(CatalogService catalogService) {
		this.catalogService = catalogService;
	}


	// metoda ta uruchamia się w momencie gdy już cała apka wstanie (wstanie kontekst Spirnga, wstanie apka )
	@Override
	public void run(String... args) throws Exception {
//		CatalogService catalogService = new CatalogService();
		List<Book> books = catalogService.findByTitle("Boy");
		books.forEach(System.out::println);

	}
}
