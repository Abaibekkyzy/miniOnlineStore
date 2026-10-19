package org.example.minionlinestore.mapper;


import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(source = "productCode", target = "code")
    ProductDTO toDTO(Product product);

    @Mapping(source = "code", target = "productCode")
    Product toModel(ProductDTO productDTO);

    List<ProductDTO> toDTOs(List<Product> products);
    List<Product> toModels(List<ProductDTO> products);
}
