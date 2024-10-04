package ClothesEcommerce.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorSize {
    private int color;
    private List<SizeRequest> values;
}
