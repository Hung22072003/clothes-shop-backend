package ClothesEcommerce.Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "theme")
    private String theme;
    @Column(name = "background")
    private String background;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductCategory> products;

    @ManyToOne
    @JoinColumn(name = "parent_category")
    private Category parent_category;

    @OneToMany(mappedBy = "parent_category", cascade = CascadeType.ALL)
    private List<Category> subcategories;

    public Category() {
    }

    public Category(int id, String name, String theme, String background, List<ProductCategory> products) {
        this.id = id;
        this.name = name;
        this.theme = theme;
        this.background = background;
        this.products = products;
    }
}
