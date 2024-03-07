package pl.umcs.coffee.order;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order changeOrderStatus(Long orderId, Status status) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        foundOrder.setStatus(status);

        return orderRepository.save(foundOrder);
    }

    @Override
    public Order updateOrder(@NotNull Order order) {
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return orderRepository.save(order);
    }

    @Override
    public Order deleteOrder(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        orderRepository.delete(foundOrder);

        return foundOrder;
    }
}
