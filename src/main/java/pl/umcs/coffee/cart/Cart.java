package pl.umcs.coffee.cart;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.*;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.user.User;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany
  @JoinTable(
      name = "Products_Carts",
      joinColumns = {@JoinColumn(name = "cart_id")},
      inverseJoinColumns = {@JoinColumn(name = "product_id")})
  private List<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    this.products.add(product);
  }

  public void removeProduct(Product product) {
    this.products.remove(
        products.stream()
            .filter(p -> Objects.equals(p.getName(), product.getName()))
            .findFirst()
            .orElse(null));
  }

  public void removeProductById(Long id) {
    this.products.remove(
            products.stream()
                    .filter(p -> Objects.equals(p.getId(), id))
                    .findFirst()
                    .orElse(null));
  }

  public void clear() {
    this.products.clear();
  }
}
