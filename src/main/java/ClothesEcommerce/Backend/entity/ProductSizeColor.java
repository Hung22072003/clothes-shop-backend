package ClothesEcommerce.Backend.entity;

import ClothesEcommerce.Backend.entity.embeddedID.ProductSizeColorId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_size_color")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeColor {
    @EmbeddedId
    private ProductSizeColorId productSizeColorId;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne
    @MapsId("id_color")
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @MapsId("id_size")
    @JoinColumn(name = "id_size")
    private Size size;

    private int quantity;
}
