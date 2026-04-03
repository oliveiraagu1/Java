package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.exception.ProductNotFoundException;
import br.com.ecommerce.domain.product.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_whenProductExists_returnsResponse() {
        Product product = Product.builder()
                .id("1").name("Tênis").price(10).build();
        ProductResponse response = new ProductResponse();
        response.setId("1");

        when(productRepository.findById("1"))
                .thenReturn(Optional.of(product));
        when(productMapper.toResponse(product))
                .thenReturn(response);

        ProductResponse result = productService.findById("1");

        assertThat(result.getId()).isEqualTo("1");
        verify(productRepository).findById("1");
    }

    @Test
    void findById_whenNotFound_throwsException() {
        when(productRepository.findById("2"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findById("2"))
                .isInstanceOf(ProductNotFoundException.class);
    }
}