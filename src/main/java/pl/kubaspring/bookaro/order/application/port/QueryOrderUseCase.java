package pl.kubaspring.bookaro.order.application.port;

import pl.kubaspring.bookaro.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}
