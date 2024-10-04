package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;
    private String theme;
    private String background;
    private List<CategoryDTO> subcategories;


    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.theme = category.getTheme();
        this.background = category.getBackground();
        this.subcategories = (category.getSubcategories().isEmpty()) ? null : category.getSubcategories().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
