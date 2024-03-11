package pl.umcs.coffee.product;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(@NotNull Product product) {
        Product foundProduct = productRepository.findByIdOrName(product.getId(), product.getName()).orElse(null);

        if (foundProduct != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return productRepository.save(product);
    }

    @Override
    public Product getProductDetails(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product getProductDetails(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        List<Product> foundProducts = productRepository.findAllById(productIds);

        if (foundProducts.size() != productIds.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return foundProducts;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProduct(Long id) {
        Product foundProduct = productRepository.findById(id).orElse(null);

        if (foundProduct == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        productRepository.deleteById(id);

        return foundProduct;
    }
}
