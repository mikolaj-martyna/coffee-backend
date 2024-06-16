package pl.umcs.coffee.order;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.user.User;

public class OrderMapper {
  public static OrderDTO toOrderDTO(@NotNull Order order) {
    List<Long> productIds = new ArrayList<>();
    for (Product p : order.getProducts()) productIds.add(p.getId());

    return OrderDTO.builder()
        .id(order.getId())
        .status(order.getStatus())
        .date(order.getDate())
        .userId(order.getUser().getId())
        .productIds(productIds)
        .build();
  }

  public static Order toOrder(@NotNull OrderDTO orderDTO, User user, List<Product> products) {
    return Order.builder()
        .id(orderDTO.getId())
        .status(orderDTO.getStatus())
        .date(orderDTO.getDate())
        .user(user)
        .products(products)
        .build();
  }

  public static JSONArray toPayu(@NotNull Order order) {
    JSONArray productsArray = new JSONArray();

    for (Product p : order.getProducts()) {
      JSONObject productObject = new JSONObject();
      productObject.put("name", p.getName());
      productObject.put("unitPrice", p.getPrice());
      productObject.put("quantity", 1);
      productsArray.put(productObject);
    }

    return productsArray;
  }
}
