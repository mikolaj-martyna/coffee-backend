package pl.umcs.coffee.product;

import org.jetbrains.annotations.NotNull;

public class ProductMapper {
  public static ProductDTO toProductDTO(@NotNull Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .category(product.getCategory())
        .description(product.getDescription())
        .price(product.getPrice())
        .imagePath(product.getImagePath())
        .build();
  }

  public static Product toProduct(@NotNull ProductDTO productDTO) {
    return Product.builder()
            .id(productDTO.getId())
            .name(productDTO.getName())
            .category(productDTO.getCategory())
            .description(productDTO.getDescription())
            .price(productDTO.getPrice())
            .imagePath(productDTO.getImagePath())
            .build();
  }
}
