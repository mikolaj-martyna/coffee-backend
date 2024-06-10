package pl.umcs.coffee.order;

import java.util.List;

public interface OrderService {
  OrderDTO createOrder(String token);

  OrderDTO getOrder(Long orderId);

  List<OrderDTO> getAllOrdersForUser(String token);

  List<OrderDTO> getAllOrders();

  OrderDTO updateOrderStatus(OrderDTO order);

  OrderDTO deleteOrder(Long orderId);
}
