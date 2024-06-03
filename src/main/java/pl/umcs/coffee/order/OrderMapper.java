package pl.umcs.coffee.order;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.user.User;

public class OrderMapper {
  public static OrderDTO toOrderDTO(@NotNull Order order) {
    List<Long> productIds = new ArrayList<>();
    for (Product p : order.getProducts()) productIds.add(p.getId());

    return OrderDTO.builder()
        .id(order.getId())
        .status(order.getStatus())
        .userId(order.getUser().getId())
        .productIds(productIds)
        .build();
  }

  public static Order toOrder(@NotNull OrderDTO orderDTO, User user, List<Product> products) {
    return Order.builder()
        .id(orderDTO.getId())
        .status(orderDTO.getStatus())
        .user(user)
        .products(products)
        .build();
  }
}
