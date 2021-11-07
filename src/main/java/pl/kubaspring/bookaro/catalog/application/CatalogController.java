package pl.kubaspring.bookaro.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.kubaspring.bookaro.catalog.domain.Book;
import pl.kubaspring.bookaro.catalog.domain.CatalogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    public List<Book> findByTitle(String title){
        return catalogService.findByTitle(title);
    }

}
