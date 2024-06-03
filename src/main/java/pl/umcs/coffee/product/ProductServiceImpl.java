package pl.umcs.coffee.product;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  ProductServiceImpl(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductDTO addProduct(@NotNull ProductDTO productDTO) {
    Product foundProduct = productRepository.findByName(productDTO.getName()).orElse(null);

    if (foundProduct != null) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    return ProductMapper.toProductDTO(productRepository.save(ProductMapper.toProduct(productDTO)));
  }

  @Override
  public List<ProductDTO> addProducts(@NotNull List<ProductDTO> productDTOs) {
    List<ProductDTO> addedProductDTOs = new ArrayList<>();

    for (ProductDTO productDTO : productDTOs) {
      addedProductDTOs.add(addProduct(productDTO));
    }

    return addedProductDTOs;
  }

  @Override
  public ProductDTO getProductDetails(Long id) {
    Product foundProduct = productRepository.findById(id).orElse(null);

    if (foundProduct == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return ProductMapper.toProductDTO(foundProduct);
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    List<Product> foundProducts = productRepository.findAll();

    List<ProductDTO> productsDTO = new ArrayList<>();
    for (Product foundProduct : foundProducts)
      productsDTO.add(ProductMapper.toProductDTO(foundProduct));

    return productsDTO;
  }

  @Override
  public ProductDTO deleteProduct(Long id) {
    Product foundProduct = productRepository.findById(id).orElse(null);

    if (foundProduct == null) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    productRepository.deleteById(id);

    return ProductMapper.toProductDTO(foundProduct);
  }
}
