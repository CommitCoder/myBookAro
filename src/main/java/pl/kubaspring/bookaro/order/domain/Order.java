package pl.kubaspring.bookaro.order.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;

    @Builder.Default
    private OrderStatus order = OrderStatus.NEW;

    private List<OrderItem> items;
    private Recipient recipient;
    private LocalDateTime createdAt;

    BigDecimal totalPrice(){
        return items.stream()
                .map(item ->item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}