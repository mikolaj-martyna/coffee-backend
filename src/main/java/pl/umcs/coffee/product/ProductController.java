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

    @PostMapping("add/one")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PostMapping("add/multiple")
    public List<ProductDTO> addProducts(@RequestBody List<ProductDTO> productDTO) {
        return productService.addProducts(productDTO);
    }

    @GetMapping("get/{id}")
    public ProductDTO getProductDetails(@PathVariable Long id) {
        return productService.getProductDetails(id);
    }

    @GetMapping("get/all")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("delete/{id}")
    public ProductDTO deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
