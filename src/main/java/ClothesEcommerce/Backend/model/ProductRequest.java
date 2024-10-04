package ClothesEcommerce.Backend.model;

import ClothesEcommerce.Backend.dto.ProductDescriptionDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private Long original_price;
    private Long sell_price;
    @Max(value =  1, message = "DISCOUNT_INVALID")
    private Double discount;
    private String material;
    private String fit;
    private String type;
    private int quantity;
    List<ProductDescriptionDTO> descriptions;
    private List<Integer> categories;
    private List<ColorSize> color_sizes;
}
