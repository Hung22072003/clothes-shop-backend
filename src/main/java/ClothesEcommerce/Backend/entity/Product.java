package ClothesEcommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Long original_price;
    private Long sell_price;
    private Double discount;
    private String material;
    private String fit;
    private String type;
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDescription> productDescriptions;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCategory> productCategories;

    @OneToMany(mappedBy = "product", cascade =  CascadeType.ALL)
    private List<ProductImage> productImageList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductColor> productColors;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSizeColor> productSizeColors;
    public Product() {
    }

    public Product(String name, Long original_price, Long sell_price, Double discount,String material, String fit, int quantity, String type) {
        this.name = name;
        this.original_price = original_price;
        this.sell_price = sell_price;
        this.discount = discount;
        this.material = material;
        this.fit = fit;
        this.quantity = quantity;
        this.type = type;
    }
}
