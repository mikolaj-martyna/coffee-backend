package pl.umcs.coffee.cart;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import pl.umcs.coffee.product.ProductDTO;

@RestController
@RequestMapping("cart")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("get")
    public List<ProductDTO> getCart(@RequestHeader(name="Authorization") String token) {
        return cartService.getCart(token.split(" ")[1]);
    }

    @PostMapping("add")
    public List<ProductDTO> addToCart(@RequestHeader(name="Authorization") String token, @RequestBody List<Long> productIDs) {
        return cartService.updateCart(token.split(" ")[1], productIDs, Action.ADD);
    }

    @PutMapping("remove")
    public List<ProductDTO> removeFromCart(@RequestHeader(name="Authorization") String token, @RequestBody List<Long> productIDs) {
        return cartService.updateCart(token.split(" ")[1], productIDs, Action.REMOVE);
    }

    @DeleteMapping("clear")
    public void clearCart(@RequestHeader(name="Authorization") String token) {
        cartService.updateCart(token.split(" ")[1], new ArrayList<>(), Action.CLEAR);
    }
}
