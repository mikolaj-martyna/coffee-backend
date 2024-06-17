package pl.umcs.coffee.payment;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("payment")
public class PaymentController {
  private final PaymentService paymentServiceImpl;

  public PaymentController(PaymentServiceImpl paymentServiceImpl) {
    this.paymentServiceImpl = paymentServiceImpl;
  }
}
