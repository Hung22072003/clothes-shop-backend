package ClothesEcommerce.Backend.repository;

import ClothesEcommerce.Backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query(value = "select p from Product p inner join ProductCategory pc on p.id = pc.productCategoryId.id_product where pc.productCategoryId.id_category = :id_category")
    Page<Product> findProductsByCategory(@Param("id_category") int id_category, Pageable pageable);

    boolean existsByName(String name);
    @Query(value = "SELECT * FROM product WHERE LOWER(unaccent(name)) LIKE LOWER(unaccent(CONCAT('%', :name, '%')))", nativeQuery = true)
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
