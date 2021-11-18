package pl.kubaspring.bookaro.catalog.application.port;

import lombok.Builder;
import lombok.Value;
import pl.kubaspring.bookaro.catalog.domain.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface CatalogUseCase {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    Optional<Book> findOneByTitle(String title);

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    void addBook(CreateBookCommand createBookCommand);

    void removeById(Long id);

    UpdateBookResponse updateBook(UpdateBookCommand updateBookCommand);

    List<Book> findByAuthor(String author);

    @Value
     class CreateBookCommand{
         String title;
         String author;
         Integer year;
         BigDecimal price;

         public Book toBook(){
             return new Book(title, author, year, price);
         }
    }

    @Value
    @Builder
    class UpdateBookCommand {
        Long id;
        String title;
        String author;
        Integer year;

        public Book updateFields(Book book){
            if(title!=null){
                book.setTitle(title);
            }
            if(author!=null){
                book.setAuthor(author);
            }
            if(year!=null){
                book.setYear(year);
            }
            return book;
        }
    }

    @Value
    class UpdateBookResponse{
        public static UpdateBookResponse SUCCESS = new UpdateBookResponse(true, emptyList());
        boolean success;
        List<String> errors;
    }

}
