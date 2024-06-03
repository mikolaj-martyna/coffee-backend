package pl.umcs.coffee.order;

import jakarta.persistence.*;
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
    @GeneratedValue
    private Long id;
    private Status status;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
