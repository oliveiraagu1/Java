package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.exception.ProductNotFoundException;
import br.com.ecommerce.domain.product.dto.ProductRequest;
import br.com.ecommerce.domain.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }

    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toResponse(product);
    }

    public ProductResponse create(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        product.setActive(true);
        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse update(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productMapper.updateFromRequest(productRequest, product);
        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

}
