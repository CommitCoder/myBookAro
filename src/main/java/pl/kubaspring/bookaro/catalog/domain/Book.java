package pl.kubaspring.bookaro.catalog.domain;

import lombok.*;

import java.math.BigDecimal;

//@Getter
//@Setter
//@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer year;
    private BigDecimal price;

    public Book(String title, String author, Integer year, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }
    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
