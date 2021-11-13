package pl.kubaspring.bookaro.catalog.domain;

import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {
    private  Long id;
    private  String title;
    private  String author;
    private  Integer year;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
