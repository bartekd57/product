package pl.domanski.product;

public class Mapper {

    static ProductDTO toDto(Product product){
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setType(product.getType().name());
        dto.setPrice(product.getPrice());
        return dto;
    }

}
