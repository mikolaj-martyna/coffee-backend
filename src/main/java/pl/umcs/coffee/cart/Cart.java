package pl.umcs.coffee.cart;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private User user;

  @OneToMany private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
