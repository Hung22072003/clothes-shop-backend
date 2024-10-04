package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.ProductDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDescriptionDTO {
    private String title;
    private String description;
    private int order;

    public ProductDescriptionDTO(ProductDescription productDescription) {
        this.title = productDescription.getTitle();
        this.description = productDescription.getDescription();
        this.order = productDescription.getOrder();
    }

}
