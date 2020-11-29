package pl.domanski.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.domanski.product.model.ProductDTO;

@RestController
@RequestMapping("")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PatchMapping("/product/{id}")
    public ProductDTO getProductWithDiscountById(@PathVariable Long id) {
        return productService.findProductAndChangePrice(id);
    }

    @GetMapping("/product/counter/{id}")
    public Integer getNumber(@PathVariable Long id) {
        return productService.getCounterValueForProduct(id);
    }


}
