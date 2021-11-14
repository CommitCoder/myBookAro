package pl.kubaspring.bookaro.order.domain;

import pl.kubaspring.bookaro.catalog.domain.Book;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> findAll();
    Order save(Order order);

    Optional<Order> findById(Long id);

    void removeById(Long id);



}
