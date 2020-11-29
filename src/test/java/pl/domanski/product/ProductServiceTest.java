package pl.domanski.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import pl.domanski.product.model.*;
import pl.domanski.product.repositories.DiscountRepository;
import pl.domanski.product.repositories.ProductRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Spy
    private Mapper mapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DiscountRepository discountRepository;



    @Test
    public void findProductAndChangePrice() {
        //Given
        Product product = createProduct(ProductType.FEMALE, new BigDecimal(100));
        Discount discount = createDiscount(new BigDecimal(5));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        when(discountRepository.findByType(any(ProductType.class))).thenReturn(Optional.of(discount));

        //When
        ProductDTO productById = productService.findProductAndChangePrice(1L);

        //Then
        Assert.assertEquals(productById.getPrice(), BigDecimal.valueOf(95).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void getProductWithDiscountedPrice() {
        //Given
        Product product = createProduct(ProductType.KID, new BigDecimal(100));
        Discount discount = createDiscount(new BigDecimal(10));
        when(discountRepository.findByType(any(ProductType.class))).thenReturn(Optional.of(discount));

        //When
        Product productWithDiscountedPrice = productService.getProductWithDiscountedPrice(product);

        //Given
        Assert.assertEquals(productWithDiscountedPrice.getPrice(),BigDecimal.valueOf(90).setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    public void findProductById() {
        //Given
        Product product = preparedProduct();
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        //When
        ProductDTO productById = productService.findProductById(1L);

        //Then
        Assert.assertEquals(productById.getType(), "MALE");
    }



    private Discount createDiscount(BigDecimal decimal) {
        Discount discount = new Discount();
        discount.setDiscount(decimal);
        return discount;
    }

    private Product createProduct(ProductType productType, BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        product.setId(Long.valueOf(1));
        product.setCounter(1);
        product.setType(productType);
        return product;
    }

    private Product preparedProduct(){
        return new Product(2L, "pasta", "do zębów", ProductType.MALE, BigDecimal.valueOf(10), 3);
    }


}