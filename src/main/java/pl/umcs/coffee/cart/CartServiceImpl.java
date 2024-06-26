package pl.umcs.coffee.cart;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.product.ProductDTO;
import pl.umcs.coffee.product.ProductMapper;
import pl.umcs.coffee.product.ProductRepository;
import pl.umcs.coffee.security.JwtService;
import pl.umcs.coffee.user.User;
import pl.umcs.coffee.user.UserRepository;

@Service
public class CartServiceImpl implements CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final ProductRepository productRepository;

  public CartServiceImpl(
          CartRepository cartRepository, UserRepository userRepository, JwtService jwtService, ProductRepository productRepository) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.productRepository = productRepository;
  }

  public List<ProductDTO> updateCart(String token, List<Long> productIDs, Action action) {
    // Get user from token
    Optional<User> user = userRepository.findByEmail(jwtService.extractUsername(token));

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Get products from DTOs
    // and add/remove them to/from user's cart
    // or clear it completely
    Cart cart = user.get().getCart();

    switch (action) {
      case ADD -> {
        for (Long productID : productIDs)
          cart.addProduct(productRepository.findById(productID).orElseThrow());
      }
      case REMOVE -> {
        for (Long productID : productIDs)
          cart.removeProductById(productID);
      }
      case CLEAR -> cart.clear();
    }

    // Save changes
    cartRepository.save(cart);

    // Return current cart
    return ProductMapper.toProductDTOs(cart.getProducts());
  }

  public List<ProductDTO> getCart(String token) {
    Optional<User> user = userRepository.findByEmail(jwtService.extractUsername(token));

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return ProductMapper.toProductDTOs(user.get().getCart().getProducts());
  }
}
