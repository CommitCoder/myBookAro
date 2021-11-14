package pl.kubaspring.bookaro.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kubaspring.bookaro.order.application.port.QueryOrderUseCase;
import pl.kubaspring.bookaro.order.domain.Order;
import pl.kubaspring.bookaro.order.domain.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
