package pl.umcs.coffee.cart;

import pl.umcs.coffee.product.ProductDTO;

import java.util.List;

public interface CartService {
    List<ProductDTO> updateCart(String token, List<ProductDTO> productDTOs, Action action);

    List<ProductDTO> getCart(String token);
}
