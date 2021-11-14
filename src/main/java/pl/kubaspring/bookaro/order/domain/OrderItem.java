package pl.kubaspring.bookaro.order.domain;

import lombok.Value;
import pl.kubaspring.bookaro.catalog.domain.Book;
@Value
public class OrderItem {
    Book book;
    int quantity;
}
