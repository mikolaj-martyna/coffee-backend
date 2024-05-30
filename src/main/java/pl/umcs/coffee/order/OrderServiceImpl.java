package pl.umcs.coffee.order;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.product.ProductServiceImpl;
import pl.umcs.coffee.user.User;
import pl.umcs.coffee.user.UserRepository;
import pl.umcs.coffee.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    OrderServiceImpl(final UserServiceImpl userService, final ProductServiceImpl productService, final OrderRepository orderRepository, UserRepository userRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDTO createOrder(@NotNull OrderDTO orderDTO) {
        Optional<User> user = userRepository.findById(orderDTO.getUserId());

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        List<Product> products = productService.getProductsByIds(orderDTO.getProductIds());
        Order createdOrder = orderRepository.save(OrderMapper.toOrder(orderDTO, user.get(), products));

        return OrderMapper.toOrderDTO(createdOrder);
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
    public List<OrderDTO> getAllOrdersForUser(Long userId) {
        List<Order> userOrders = orderRepository.findAllByUserId(userId);
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
    public OrderDTO changeOrderStatus(Long orderId, Status status) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        foundOrder.setStatus(status);

        return OrderMapper.toOrderDTO(orderRepository.save(foundOrder));
    }

    @Override
    public OrderDTO updateOrder(@NotNull OrderDTO orderDTO) {
        Order foundOrder = orderRepository.findById(orderDTO.getId()).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

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
