package pl.umcs.coffee.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


}
