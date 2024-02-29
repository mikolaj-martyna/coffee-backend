package pl.umcs.coffee.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.coffee.product.Product;
import pl.umcs.coffee.user.User;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Status status;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    private enum Status {
        AWAITING_PAYMENT,
        PAID,
        PREPARATION,
        SHIPPING,
        DELIVERED
    }
}
