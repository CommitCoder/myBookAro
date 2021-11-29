package pl.kubaspring.bookaro.catalog.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kubaspring.bookaro.catalog.application.port.CatalogUseCase;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/catalog")
@RestController
@AllArgsConstructor
public class CatalogController {
    private final CatalogUseCase catalog;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll( @RequestParam Optional<String>title,
                              @RequestParam Optional<String>author){
        if(title.isPresent() && author.isPresent()){
            return catalog.findByTitleAndAuthor(title.get(), author.get());
        }else if(title.isPresent()){
            return catalog.findByTitle(title.get());
        }else if(author.isPresent()){
         return catalog.findByAuthor(author.get());
        }
        return catalog.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return catalog
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addBook(@RequestBody RestCreateBookCommand command){
        Book book = catalog.addBook(command.toCommand());
        return ResponseEntity.created(createdBookUri(book)).build();
    }

    private URI createdBookUri(Book book) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + book.getId().toString()).build().toUri();
    }

    @Data
    private static class RestCreateBookCommand{
        private String title;
        private String author;
        private Integer year;
        private BigDecimal price;

        CatalogUseCase.CreateBookCommand toCommand(){
            return new CatalogUseCase.CreateBookCommand(
                    title, author, year, price);

        }

    }






}
