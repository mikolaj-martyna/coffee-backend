package pl.umcs.coffee.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
  private Long id;

  private String name;
  private String category;
  private String description;

  private long price;

  private String imagePath;
}
