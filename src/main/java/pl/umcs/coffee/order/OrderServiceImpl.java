package pl.umcs.coffee.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.cart.Action;
import pl.umcs.coffee.cart.CartServiceImpl;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.product.ProductRepository;
import pl.umcs.coffee.security.JwtService;
import pl.umcs.coffee.user.User;
import pl.umcs.coffee.user.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final ProductRepository productRepository;
  private final CartServiceImpl cartServiceImpl;

  OrderServiceImpl(
          final OrderRepository orderRepository,
          UserRepository userRepository,
          JwtService jwtService,
          ProductRepository productRepository, CartServiceImpl cartServiceImpl) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.productRepository = productRepository;
    this.cartServiceImpl = cartServiceImpl;
  }

  @Override
  public OrderDTO createOrder(String token) {
    Optional<User> user = userRepository.findByEmail(jwtService.extractUsername(token));

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    List<Product> products =
        productRepository.findAllById(
            user.get().getCart().getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));

    if (products.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No products in cart");
    }

    Order createdOrder = new Order(0L, Status.AWAITING_PAYMENT, LocalDateTime.now(), user.get(), products);
    cartServiceImpl.updateCart(token, new ArrayList<>(), Action.CLEAR);

    return OrderMapper.toOrderDTO(orderRepository.save(createdOrder));
  }

  @Override
  public OrderDTO getOrder(Long orderId) {
    Order foundOrder = orderRepository.findById(orderId).orElse(null);

    if (foundOrder == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return OrderMapper.toOrderDTO(foundOrder);
  }

  @Override
  public List<OrderDTO> getAllOrdersForUser(String token) {
    List<Order> userOrders = orderRepository.findAllByUserEmail(jwtService.extractUsername(token));
    List<OrderDTO> userOrdersDTO = new ArrayList<>();

    for (Order order : userOrders) {
      userOrdersDTO.add(OrderMapper.toOrderDTO(order));
    }

    return userOrdersDTO;
  }

  @Override
  public List<OrderDTO> getAllOrders() {
    List<Order> allOrders = orderRepository.findAll();
    List<OrderDTO> allOrdersDTO = new ArrayList<>();

    for (Order order : allOrders) {
      allOrdersDTO.add(OrderMapper.toOrderDTO(order));
    }

    return allOrdersDTO;
  }

  @Override
  public OrderDTO updateOrder(@NotNull OrderDTO orderDTO) {
    Order foundOrder = orderRepository.findById(orderDTO.getId()).orElse(null);

    if (foundOrder == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    foundOrder.setStatus(orderDTO.getStatus());

    return OrderMapper.toOrderDTO(orderRepository.save(foundOrder));
  }

  @Override
  public OrderDTO deleteOrder(Long orderId) {
    Order foundOrder = orderRepository.findById(orderId).orElse(null);

    if (foundOrder == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    orderRepository.delete(foundOrder);

    return OrderMapper.toOrderDTO(foundOrder);
  }
}
