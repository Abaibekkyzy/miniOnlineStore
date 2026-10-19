package org.example.minionlinestore.api;

import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.model.Product;
import org.example.minionlinestore.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
