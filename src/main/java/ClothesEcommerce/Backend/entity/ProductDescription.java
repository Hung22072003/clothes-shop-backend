package ClothesEcommerce.Backend.entity;

import ClothesEcommerce.Backend.dto.ProductDescriptionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDescription {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_description;
    private String title;
    @Column(name = "description", length = 5000)
    private String description;
    @Column(name = "\"order\"")
    private int order;

    @ManyToOne()
    @JoinColumn(name = "id_product")
    private Product product;

    public ProductDescription(ProductDescriptionDTO productDescriptionDTO) {
        this.title = productDescriptionDTO.getTitle();
        this.description = productDescriptionDTO.getDescription();
        this.order = productDescriptionDTO.getOrder();
    }
}
