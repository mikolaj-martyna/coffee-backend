package pl.umcs.coffee.cart;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.product.ProductDTO;
import pl.umcs.coffee.product.ProductMapper;
import pl.umcs.coffee.security.JwtService;
import pl.umcs.coffee.user.User;
import pl.umcs.coffee.user.UserRepository;

@Service
public class CartServiceImpl implements CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final JwtService jwtService;

  public CartServiceImpl(
      CartRepository cartRepository, UserRepository userRepository, JwtService jwtService) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  public List<ProductDTO> updateCart(String token, List<ProductDTO> productDTOs, Action action) {
    // Get user from token
    Optional<User> user = userRepository.findByEmail(jwtService.extractUsername(token));

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Get products from DTOs
    // and add them to user's cart
    Cart cart = user.get().getCart();

    switch (action) {
      case ADD -> {
        for (ProductDTO productDTO : productDTOs)
          cart.addProduct(ProductMapper.toProduct(productDTO));
      }
      case REMOVE -> {
        for (ProductDTO productDTO : productDTOs)
          cart.removeProduct(ProductMapper.toProduct(productDTO));
      }
      case CLEAR -> cart.getProducts().clear();
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
