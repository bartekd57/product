package pl.domanski.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PatchMapping("/product/{id}")
    public ProductDTO getProductWithDiscountById(@PathVariable Long id) {
        return productService.findProductAndChangePrice(id);
    }

    @GetMapping("/product/{id}")
    public Integer getNumber(@PathVariable Long id) {
        return productService.getCounterValueForProduct(id);
    }


}
