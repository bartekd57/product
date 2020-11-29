package pl.domanski.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDTO> getProductOrdinaryPriceById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductById(id));
    }


    @GetMapping("/productDiscounted/{id}")
    public ResponseEntity<ProductDTO> getProductWithDiscountById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductAndChangePrice(id));
    }

    @GetMapping("/product/counter/{id}")
    public ResponseEntity<Integer> getNumber(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getCounterValueForProduct(id));
    }


}
