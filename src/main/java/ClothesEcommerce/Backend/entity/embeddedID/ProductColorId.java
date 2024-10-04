package ClothesEcommerce.Backend.entity.embeddedID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorId implements Serializable {
    @Column(name = "id_product")
    private int id_product;
    @Column(name = "id_color")
    private int id_color;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ProductColorId that)) return false;
        return getId_product() == that.getId_product() && getId_color() == that.getId_color();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_product(), getId_color());
    }
}
