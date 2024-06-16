package pl.umcs.coffee.payment;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentServiceImpl;

    public PaymentController(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @PostMapping
    public RedirectView payment(@RequestHeader(name = "Authorization") String token) {
        return paymentServiceImpl.handlePayment(token.split(" ")[1]);
    }
}
