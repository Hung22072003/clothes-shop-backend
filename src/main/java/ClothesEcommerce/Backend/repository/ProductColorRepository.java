package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.ProductColor;
import ClothesEcommerce.Backend.entity.embeddedID.ProductColorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorId> {
}
