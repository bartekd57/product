package pl.domanski.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.domanski.product.model.Product;
import pl.domanski.product.model.ProductDTO;
import pl.domanski.product.model.ProductType;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Spy
    private Mapper mapper;

    @Mock
    private ProductService productService;


    @Test
    public void shouldGetProductWithDiscountById() {
        //Given
        Product product = preparedProduct();
        ProductDTO productDTO = mapper.toDto(product);
        when(productService.findProductAndChangePrice(any(Long.class))).thenReturn(productDTO);

        //When
         ResponseEntity<ProductDTO> productWithDiscountById = productController.getProductWithDiscountById(1L);

        //Then
        Assert.assertEquals(productWithDiscountById.getBody().getType(), "FEMALE");
    }


    @Test
    public void shouldGetNumber() {
        //Given
        Product product = preparedProduct();
        Integer counter = product.getCounter();
        when(productService.getCounterValueForProduct(any(Long.class))).thenReturn(counter);

        //When
        ResponseEntity<Integer> number = productController.getNumber(1L);

        //Then
        Assert.assertEquals(Integer.valueOf(number.getBody()), counter);
    }


    private Product preparedProduct() {
        return new Product(1L, "pasta", "do zębów", ProductType.FEMALE, BigDecimal.valueOf(10), 3);
    }

}