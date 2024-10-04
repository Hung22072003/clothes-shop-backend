package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.Category;
import ClothesEcommerce.Backend.entity.Product;
import ClothesEcommerce.Backend.entity.ProductCategory;
import ClothesEcommerce.Backend.entity.embeddedID.ProductCategoryId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId>, JpaSpecificationExecutor<ProductCategory> {
    Page<ProductCategory> findAllByCategory(Category category, Pageable pageable);
}
