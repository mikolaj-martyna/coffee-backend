package pl.umcs.coffee.inventory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.coffee.product.Product;

@Getter
@Setter
@Entity
@Table
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;
    private Long amount;

    @OneToOne
    private Product product;
}
