package pl.umcs.coffee.product;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);
    List<ProductDTO> addProducts(List<ProductDTO> productDTOs);

    ProductDTO getProductDetails(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO deleteProduct(Long id);
}
