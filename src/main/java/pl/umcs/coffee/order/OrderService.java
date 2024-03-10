package pl.umcs.coffee.order;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order);

    OrderDTO getOrder(Long orderId);
    List<OrderDTO> getAllOrdersForUser(Long userId);
    List<OrderDTO> getAllOrders();

    OrderDTO changeOrderStatus(Long orderId, Status status);
    OrderDTO updateOrder(OrderDTO order);

    OrderDTO deleteOrder(Long orderId);
}
