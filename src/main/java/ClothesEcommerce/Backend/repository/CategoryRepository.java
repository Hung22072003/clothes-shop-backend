package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
