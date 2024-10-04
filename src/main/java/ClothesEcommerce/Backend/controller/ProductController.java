package ClothesEcommerce.Backend.controller;

import ClothesEcommerce.Backend.dto.CategoryDTO;
import ClothesEcommerce.Backend.dto.ProductDTO;
import ClothesEcommerce.Backend.model.ApiResponse;
import ClothesEcommerce.Backend.model.ColorSize;
import ClothesEcommerce.Backend.model.ProductRequest;
import ClothesEcommerce.Backend.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    @GetMapping("/getByCategory/{id}")
    public ApiResponse<List<ProductDTO>> getProductsByCategory(
            @PathVariable int id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.getProductsByCategory(id, page, size));
        return apiResponse;
    }

    @GetMapping("/getProductById/{id}")
    public ApiResponse<ProductDTO> getProductById(
            @PathVariable int id
    ) {
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.getProductById(id));
        return apiResponse;
    }
    @PostMapping("/addProduct")
    public ApiResponse<ProductDTO> addProduct(
            @RequestPart(name = "product") @Valid ProductRequest productRequest,
            @RequestParam(name = "images", required = false) List<MultipartFile> images
    ) {
        ApiResponse<ProductDTO> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.addProduct(productRequest, images));
        return apiResponse;
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductDTO>> searchProducts(
            @RequestParam(name = "query") String query,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size
    ) {
        ApiResponse<List<ProductDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.searchProducts(query, page, size));
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteProduct(
            @PathVariable("id") int id
    ) {
        productService.deleteProduct(id);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult("Delete product successfully");
        return apiResponse;
    }
}
