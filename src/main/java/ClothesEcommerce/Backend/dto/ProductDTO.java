package ClothesEcommerce.Backend.dto;

import ClothesEcommerce.Backend.entity.Product;
import ClothesEcommerce.Backend.entity.ProductCategory;
import ClothesEcommerce.Backend.entity.ProductDescription;
import ClothesEcommerce.Backend.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private Long original_price;
    private Long sell_price;
    private Double discount;
    private String material;
    private String fit;
    private String type;
    private int quantity;
    private List<ProductDescriptionDTO> descriptions;
    private List<String> images;
    private List<ColorDTO> colors;
    private List<SizeColorDTO> sizes;
    public ProductDTO(ProductCategory productCategory) {
        this.id = productCategory.getProduct().getId();
        this.name = productCategory.getProduct().getName();
        this.original_price = productCategory.getProduct().getOriginal_price();
        this.sell_price = productCategory.getProduct().getSell_price();
        this.quantity = productCategory.getProduct().getQuantity();
        this.discount = productCategory.getProduct().getDiscount();
        this.material = productCategory.getProduct().getMaterial();
        this.fit = productCategory.getProduct().getFit();
        this.type = productCategory.getProduct().getType();
        this.images = productCategory.getProduct().getProductImageList().stream().map(ProductImage::getName).toList();
        this.descriptions = productCategory.getProduct().getProductDescriptions().stream().map(ProductDescriptionDTO::new).toList();
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.original_price = product.getOriginal_price();
        this.sell_price = product.getSell_price();
        this.quantity =product.getQuantity();
        this.discount = product.getDiscount();
        this.material = product.getMaterial();
        this.fit = product.getFit();
        this.type = product.getType();
        this.images = (product.getProductImageList() != null) ? product.getProductImageList().stream().map(ProductImage::getName).toList() : List.of();
        this.descriptions = (product.getProductDescriptions() != null) ?  product.getProductDescriptions().stream().map(ProductDescriptionDTO::new).toList() : List.of();
    }
}
