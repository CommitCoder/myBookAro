package pl.kubaspring.bookaro.order.domain;

import lombok.Value;
import pl.kubaspring.bookaro.catalog.domain.Book;
@Value
public class OrderItem {
    Long bookId;
    int quantity;
}
