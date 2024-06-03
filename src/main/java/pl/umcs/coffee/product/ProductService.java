package pl.umcs.coffee.product;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);
    List<ProductDTO> addProducts(List<ProductDTO> productDTOs);

    ProductDTO getProductDetails(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO deleteProduct(Long id);
}
