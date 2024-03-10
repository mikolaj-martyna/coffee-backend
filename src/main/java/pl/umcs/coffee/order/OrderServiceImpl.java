package pl.umcs.coffee.order;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.product.ProductServiceImpl;
import pl.umcs.coffee.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;

    private final OrderRepository orderRepository;

    OrderServiceImpl(final UserServiceImpl userService, final ProductServiceImpl productService, final OrderRepository orderRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO createOrder(@NotNull OrderDTO order) {
        Order createdOrder = orderRepository.save(Order.builder()
                .status(Status.AWAITING_PAYMENT)
                .user(userService.getUser(order.getUserId()))
                .products(productService.getProductsByIds(order.getProductIds()))
                .build());

        return toDTO(createdOrder);
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return toDTO(foundOrder);
    }

    @Override
    public List<OrderDTO> getAllOrdersForUser(Long userId) {
        List<Order> userOrders = orderRepository.findAllByUserId(userId);
        List<OrderDTO> userOrdersDTO = new ArrayList<>();

        for (Order order : userOrders) {
            userOrdersDTO.add(toDTO(order));
        }

        return userOrdersDTO;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        List<OrderDTO> allOrdersDTO = new ArrayList<>();

        for (Order order : allOrders) {
            allOrdersDTO.add(toDTO(order));
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
        Order changedOrder = orderRepository.save(foundOrder);

        return toDTO(changedOrder);
    }

    @Override
    public OrderDTO updateOrder(@NotNull OrderDTO order) {
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // TODO: add update order functionality

        return order;
    }

    @Override
    public OrderDTO deleteOrder(Long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        orderRepository.delete(foundOrder);

        return toDTO(foundOrder);
    }

    private OrderDTO toDTO(@NotNull Order order) {
        List<Long> productIds = new ArrayList<>();
        for (Product p : order.getProducts()) productIds.add(p.getId());

        return OrderDTO.builder().id(order.getId()).status(order.getStatus()).userId(order.getUser().getId()).productIds(productIds).build();
    }
}
