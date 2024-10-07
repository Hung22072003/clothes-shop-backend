package ClothesEcommerce.Backend.service;

import ClothesEcommerce.Backend.dto.ProductDTO;
import ClothesEcommerce.Backend.entity.ProductImage;
import ClothesEcommerce.Backend.model.PaginationObjectResponse;
import ClothesEcommerce.Backend.model.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    PaginationObjectResponse getProductsByCategory(int id, int page, int size);

    ProductDTO addProduct(ProductRequest productRequest, List<MultipartFile> images);

    ProductDTO getProductById(int id);

    PaginationObjectResponse searchProducts(String query, int page, int size);

    void deleteProduct(int id);

    void deleteProductImagesOnCloud(List<ProductImage> productImages);
    public List<String> getNameImages(List<ProductImage> productImages);
    public String getNameOneImage(ProductImage productImage);
}
