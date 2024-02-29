package pl.umcs.coffee.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    ProductServiceImpl productService;

    ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("add")
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("get/{id}")
    public Product getProductDetails(@PathVariable Long id) {
        return productService.getProductDetails(id);
    }

    @GetMapping("get/{name}")
    public Product getProductDetails(@PathVariable String name) {
        return productService.getProductDetails(name);
    }

    @GetMapping("get/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("delete/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
