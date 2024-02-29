package pl.umcs.coffee.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(Product product);

    Product getProductDetails(Long id);
    Product getProductDetails(String name);

    List<Product> getAllProducts();

    Product deleteProduct(Long id);
}
