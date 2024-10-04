package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.ProductSizeColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeColorDTO {
    private int id_color;
    private List<SizeDTO> values;
    public SizeColorDTO(int id_color) {
        this.id_color = id_color;
    }


}
