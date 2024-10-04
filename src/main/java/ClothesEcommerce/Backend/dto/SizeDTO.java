package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.ProductSizeColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDTO {
    private int id_size;
    private String type;
    private String parameter;
    private int quantity;

    public SizeDTO(ProductSizeColor sizeColor) {
        this.id_size = sizeColor.getSize().getId();
        this.type = sizeColor.getSize().getSize();
        this.parameter = sizeColor.getSize().getParameter();
        this.quantity = sizeColor.getQuantity();
    }
}
