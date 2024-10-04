package ClothesEcommerce.Backend.entity;

import ClothesEcommerce.Backend.entity.embeddedID.ProductColorId;
import ClothesEcommerce.Backend.entity.embeddedID.ProductSizeColorId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductColor {
    @EmbeddedId
    private ProductColorId productColorId;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne
    @MapsId("id_color")
    @JoinColumn(name = "id_color")
    private Color color;
    private String image;

    public ProductColor(ProductColorId productColorId, Product savedProduct, Color color) {
        this.productColorId = productColorId;
        this.product = savedProduct;
        this.color = color;
    }
}
