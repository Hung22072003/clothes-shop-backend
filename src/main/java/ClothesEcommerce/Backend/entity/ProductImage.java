package ClothesEcommerce.Backend.entity;

import ClothesEcommerce.Backend.repository.ProductImageRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public ProductImage(String url, Product saveProduct) {
        this.name = url;
        this.product = saveProduct;
    }
}
