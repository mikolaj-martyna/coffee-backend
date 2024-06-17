package pl.umcs.coffee.payment;

import pl.umcs.coffee.order.Order;

public interface PaymentService {
    String authorize();
    String createOrder(Order order);

    String handlePayment(String token);
}
