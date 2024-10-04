package ClothesEcommerce.Backend.service;

import ClothesEcommerce.Backend.dto.CategoryDTO;
import ClothesEcommerce.Backend.model.OptionsRequest;
import ClothesEcommerce.Backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).sorted(Comparator.comparing(CategoryDTO::getId)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategoriesByOptions(List<String> options) {
        List<CategoryDTO> categoryDTOS = getAllCategory();
        return categoryDTOS.stream().filter((categoryDTO) -> options.contains((categoryDTO.getName()))).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        return new CategoryDTO(Objects.requireNonNull(categoryRepository.findById(id).orElse(null)));
    }
}
