package pl.umcs.coffee.cart;

import org.springframework.web.bind.annotation.*;
import pl.umcs.coffee.product.ProductDTO;

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("add")
    public List<ProductDTO> addToCart(@RequestHeader(name="Authorization") String token, @RequestBody List<ProductDTO> productDTOs) {
        return cartService.updateCart(token.split(" ")[1], productDTOs, Action.ADD);
    }

    @PutMapping("remove")
    public List<ProductDTO> removeFromCart(@RequestHeader(name="Authorization") String token, @RequestBody List<ProductDTO> productDTOs) {
        return cartService.updateCart(token.split(" ")[1], productDTOs, Action.REMOVE);
    }

    @DeleteMapping("clear")
    public void clearCart(@RequestHeader(name="Authorization") String token) {
        cartService.updateCart(token.split(" ")[1], new ArrayList<>(), Action.CLEAR);
    }
}
