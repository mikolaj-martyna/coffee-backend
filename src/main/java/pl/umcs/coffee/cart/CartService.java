package pl.umcs.coffee.cart;

import pl.umcs.coffee.product.ProductDTO;

import java.util.List;

public interface CartService {
  List<ProductDTO> updateCart(String token, List<Long> productIDs, Action action);

  List<ProductDTO> getCart(String token);
}
