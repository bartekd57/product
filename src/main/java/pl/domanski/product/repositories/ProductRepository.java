package pl.domanski.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
