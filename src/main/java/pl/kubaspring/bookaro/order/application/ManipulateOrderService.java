package pl.kubaspring.bookaro.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kubaspring.bookaro.order.application.port.ManipulateOrderUseCase;
import pl.kubaspring.bookaro.order.domain.Order;
import pl.kubaspring.bookaro.order.domain.OrderRepository;
import pl.kubaspring.bookaro.order.domain.OrderStatus;

@Service
@RequiredArgsConstructor
class ManipulateOrderService implements ManipulateOrderUseCase {
    private final OrderRepository repository;

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderCommand command) {
        Order order = Order
                .builder()
                .recipient(command.getRecipient())
                .items(command.getItems())
                .build();
        Order save = repository.save(order);
        return PlaceOrderResponse.success(save.getId());
    }

    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        repository.findById(id)
                .ifPresent(order -> {
                    order.setStatus(status);
                    repository.save(order);
                });
    }
}