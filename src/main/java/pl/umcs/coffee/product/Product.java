package pl.umcs.coffee.product;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import pl.umcs.coffee.cart.Cart;
import pl.umcs.coffee.order.Order;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String description;

    private long price;

    private String imagePath;

    @ManyToMany(mappedBy = "products")
    private List<Cart> cart;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
