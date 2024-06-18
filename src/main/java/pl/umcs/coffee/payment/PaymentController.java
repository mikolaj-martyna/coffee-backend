package pl.umcs.coffee.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {
  private final PaymentService paymentServiceImpl;

  public PaymentController(PaymentServiceImpl paymentServiceImpl) {
    this.paymentServiceImpl = paymentServiceImpl;
  }
}
