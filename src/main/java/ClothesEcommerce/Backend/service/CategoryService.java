package ClothesEcommerce.Backend.service;

import ClothesEcommerce.Backend.dto.CategoryDTO;
import ClothesEcommerce.Backend.model.OptionsRequest;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();

    List<CategoryDTO> getCategoriesByOptions(List<String> options);

    CategoryDTO getCategoryById(int id);
}
