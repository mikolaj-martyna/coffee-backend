package pl.umcs.coffee.order;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.user.User;

@Builder
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Status status;

    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Products_Orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
}
