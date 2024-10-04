package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.Color;
import ClothesEcommerce.Backend.entity.Product;
import ClothesEcommerce.Backend.entity.ProductSizeColor;
import ClothesEcommerce.Backend.entity.embeddedID.ProductSizeColorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeColorRepository extends JpaRepository<ProductSizeColor, ProductSizeColorId> {
//    @Query(value = "SELECT p FROM ProductSizeColor p WHERE p.Product.id_product = :id_product and p.Color.id_color = :id_color")
    List<ProductSizeColor> findAllByProductAndColor(Product product, Color color);
}
