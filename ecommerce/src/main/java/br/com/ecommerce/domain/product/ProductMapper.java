package br.com.ecommerce.domain.product;
import br.com.ecommerce.domain.product.dto.ProductRequest;
import br.com.ecommerce.domain.product.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest request);

    void updateFromRequest(ProductRequest request, @MappingTarget Product product);
}