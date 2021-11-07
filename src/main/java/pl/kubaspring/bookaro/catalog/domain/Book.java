package pl.kubaspring.bookaro.catalog.domain;

import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
@Getter
@ToString
@RequiredArgsConstructor
public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final Integer year;

}
