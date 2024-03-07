package pl.umcs.coffee.order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    Order getOrder(Long orderId);
    List<Order> getAllOrdersForUser(Long userId);
    List<Order> getAllOrders();

    Order changeOrderStatus(Long orderId, Status status);
    Order updateOrder(Order order);

    Order deleteOrder(Long orderId);
}
