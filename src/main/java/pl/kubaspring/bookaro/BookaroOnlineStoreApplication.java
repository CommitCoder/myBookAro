package pl.kubaspring.bookaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.kubaspring.bookaro.catalog.domain.CatalogRepository;
import pl.kubaspring.bookaro.catalog.infrastructure.BestsellerCatalogRepository;
import pl.kubaspring.bookaro.catalog.infrastructure.SchoolCatalogRepository;

import java.util.Random;

@SpringBootApplication
public class BookaroOnlineStoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(BookaroOnlineStoreApplication.class, args);
	}

	@Bean
	CatalogRepository catalogRepository(){
		Random random = new Random();
		if(random.nextBoolean()){
			System.out.println(" choosed bestsellers ");
			return new BestsellerCatalogRepository();
		}else {
			System.out.println(" choosed school book ");
			return new SchoolCatalogRepository();
		}
	}



}
