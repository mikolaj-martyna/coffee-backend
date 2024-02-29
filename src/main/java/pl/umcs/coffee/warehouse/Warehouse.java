package pl.umcs.coffee.warehouse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.umcs.coffee.product.Product;

@Getter
@Setter
@Entity
@Table
public class Warehouse {
    @Id
    @GeneratedValue
    private Long id;
    private Long amount;

    @OneToOne
    private Product product;
}
