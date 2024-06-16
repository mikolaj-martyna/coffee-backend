package pl.umcs.coffee.payment;

import org.springframework.web.servlet.view.RedirectView;
import pl.umcs.coffee.order.Order;

public interface PaymentService {
    String authorize();
    String createOrder(Order order);

    RedirectView handlePayment(String token);
}
