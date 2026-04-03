package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.product.dto.ProductRequest;
import br.com.ecommerce.domain.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        return ResponseEntity.status(201).body(productResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
