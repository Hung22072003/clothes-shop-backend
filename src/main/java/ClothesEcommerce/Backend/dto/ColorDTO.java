package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.ProductColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {
    private int id;
    private String name;
    private String image;

    public ColorDTO(ProductColor productColor) {
        this.id= productColor.getColor().getId();
        this.name = productColor.getColor().getColor();
        this.image = productColor.getImage();
    }
}
