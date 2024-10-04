package ClothesEcommerce.Backend.controller;

import ClothesEcommerce.Backend.dto.CategoryDTO;
import ClothesEcommerce.Backend.model.ApiResponse;
import ClothesEcommerce.Backend.model.OptionsRequest;
import ClothesEcommerce.Backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/getCategoriesByOptions")
    public ApiResponse<List<CategoryDTO>> getCategoriesByOptions(
            @RequestParam("options") List<String> optionsRequest
            ) {
        ApiResponse<List<CategoryDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.getCategoriesByOptions(optionsRequest));
        return apiResponse;
    }

    @PostMapping("/all")
    public void getAllCategory(
            @RequestPart("options") OptionsRequest optionsRequest
    ) {
        System.out.println(optionsRequest);
    }

    @GetMapping("/getCategoryById/{id}")
    public ApiResponse<CategoryDTO> getCategoryById(
            @PathVariable("id") int id
    ) {
        ApiResponse<CategoryDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.getCategoryById(id));
        return apiResponse;
    }
}
