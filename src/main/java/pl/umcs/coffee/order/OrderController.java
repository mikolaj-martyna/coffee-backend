package pl.umcs.coffee.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    OrderServiceImpl orderService;

    OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public OrderDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }

    @GetMapping("get/{orderId}")
    public OrderDTO getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("get/user/{userId}")
    public List<OrderDTO> getAllOrdersForUser(@PathVariable Long userId) {
        return orderService.getAllOrdersForUser(userId);
    }

    @GetMapping("get/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("update")
    public OrderDTO updateOrder(@RequestBody OrderDTO order) {
        return orderService.updateOrder(order);
    }

    @PutMapping("update/{orderId}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long orderId, @RequestBody Status status) {
        return orderService.changeOrderStatus(orderId, status);
    }

    @DeleteMapping("delete/{orderId}")
    public OrderDTO deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}
