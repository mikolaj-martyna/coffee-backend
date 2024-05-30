package pl.umcs.coffee.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO);

    ProductDTO getProductDetails(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO deleteProduct(Long id);
}
