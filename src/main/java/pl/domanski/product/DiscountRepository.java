package pl.domanski.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByType(ProductType type);

}
