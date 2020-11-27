package pl.domanski.product;

import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public ProductDTO toDto(Product product){
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setType(product.getType().name());
        dto.setPrice(product.getPrice());
        return dto;
    }

}
