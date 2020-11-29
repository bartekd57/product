package pl.domanski.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domanski.product.model.*;
import pl.domanski.product.repositories.DiscountRepository;
import pl.domanski.product.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private DiscountRepository discountRepository;
    private Mapper mapper;
    private Map<Long, Integer> map = new HashMap<>();

    public ProductService() {
    }

    @Autowired
    public ProductService(ProductRepository productRepository, DiscountRepository discountRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
        this.mapper = mapper;
    }

    public ProductDTO findProductById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    public ProductDTO findProductAndChangePrice(Long id) {
        setCounterValue(id);
        return productRepository.findById(id)
                .map(this::getProductWithDiscountedPrice)
                .map(mapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }


    public void setCounterValue(Long id) {
            productRepository.findById(id).ifPresent(product -> {
                incrementCounter(product);
                putInMap(product);
            });
    }

    private void incrementCounter(Product product) {
        product.setCounter(product.getCounter() + 1);
        productRepository.save(product);
    }

    private void putInMap(Product product) {
        map.put(product.getId(), product.getCounter());
    }

    public Integer getCounterValueForProduct(Long id) {
        return map.get(id);
    }


    public Product getProductWithDiscountedPrice(Product product) {
        Discount discount = getProductDiscount(product);
        BigDecimal discountedPrice = countDiscountedPrice(discount, product);
        product.setPrice(discountedPrice);
        return product;
    }

    private BigDecimal countDiscountedPrice(Discount discount, Product product) {
        BigDecimal discountToNumber = BigDecimal.valueOf(1).subtract(discount.getDiscount().divide(BigDecimal.valueOf(100)));
        return product.getPrice().multiply(discountToNumber);
    }

    private Discount getProductDiscount(Product product) {
        ProductType type = product.getType();
        return discountRepository.findByType(type).orElseThrow(NoSuchElementException::new);
    }

}
