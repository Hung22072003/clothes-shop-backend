package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Integer> {
}
