package pl.domanski.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
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
        ProductDTO productWithDiscountById = productController.getProductWithDiscountById(1L);

        //Then
        Assert.assertEquals(productWithDiscountById.getType(), "FEMALE");
    }


    @Test
    public void shouldGetNumber() {
        //Given
        Product product = preparedProduct();
        Integer counter = product.getCounter();
        when(productService.getCounterValueForProduct(any(Long.class))).thenReturn(counter);

        //When
        Integer number = productController.getNumber(1L);

        //Then
        Assert.assertEquals(number, counter);
    }


    private Product preparedProduct() {
        return new Product(1L, "pasta", "do zębów", ProductType.FEMALE, BigDecimal.valueOf(10), 3);
    }

}