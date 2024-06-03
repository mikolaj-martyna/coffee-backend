package pl.umcs.coffee.product;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
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
    @GeneratedValue
    private Long id;

    private String name;
    private String category;
    private String description;

    private long price;

    private String imagePath;

    @ManyToMany
    private List<Order> orders;
}
