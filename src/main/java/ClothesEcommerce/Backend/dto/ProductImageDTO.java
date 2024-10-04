package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private int id;
    private String name;

    public ProductImageDTO(ProductImage productImage) {
        this.id = productImage.getId();
        this.name = productImage.getName();
    }
}
