package pl.domanski.product.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    private BigDecimal discount;

    public Discount() {
    }

    public Discount(Long id, ProductType type, BigDecimal discount) {
        this.id = id;
        this.type = type;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount1 = (Discount) o;
        return Objects.equals(id, discount1.id) &&
                type == discount1.type &&
                Objects.equals(discount, discount1.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, discount);
    }
}
