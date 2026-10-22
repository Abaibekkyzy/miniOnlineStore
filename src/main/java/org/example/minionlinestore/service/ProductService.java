package org.example.minionlinestore.service;


import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.mapper.ProductMapper;
import org.example.minionlinestore.model.Product;
import org.example.minionlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getProducts(){
        return productMapper.toDTOs(productRepository.findAll());
    }

    public ProductDTO addProduct(ProductDTO product){
        return productMapper.toDTO(productRepository.save(productMapper.toModel(product)));
    }

    public ProductDTO getProduct(Long id){
        return productMapper.toDTO(productRepository.findById(id).orElse(new Product()));
    }

    public ProductDTO updateProduct(ProductDTO product){
        return productMapper.toDTO(productRepository.save(productMapper.toModel(product)));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<ProductDTO> searchProduct(String key){
        List<ProductDTO> productModelList = productRepository.searchProduct("%"+key+"%");
        return productModelList;
    }

}
