package pl.umcs.coffee.order;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    OrderServiceImpl orderService;

    OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public Map<String, String> createOrder(@RequestHeader(name = "Authorization") String token) {
        return Collections.singletonMap("url", orderService.redirectCreateOrder(token.split(" ")[1]));
    }

    @GetMapping("get/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("get/user")
    public List<OrderDTO> getAllOrdersForUser(@RequestHeader(name = "Authorization") String token) {
        return orderService.getAllOrdersForUser(token.split(" ")[1]);
    }

    @GetMapping("get/all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("update")
    public OrderDTO updateOrderStatus(@RequestBody OrderDTO order) {
        return orderService.updateOrderStatus(order);
    }

    @DeleteMapping("delete/{orderId}")
    public OrderDTO deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}
