package pl.umcs.coffee.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.coffee.order.Order;
import pl.umcs.coffee.inventory.Inventory;

import java.util.List;

@Getter
@Setter
@Entity
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

    @OneToOne
    private Inventory inventory;

    @ManyToMany
    private List<Order> orders;
}
