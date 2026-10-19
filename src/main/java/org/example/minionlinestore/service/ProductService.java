package org.example.minionlinestore.service;


import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.model.Product;
import org.example.minionlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}
