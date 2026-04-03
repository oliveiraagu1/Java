package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.product.utils.SlugUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean active;

    @PrePersist
    @PreUpdate
    private void generateSlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }
}
