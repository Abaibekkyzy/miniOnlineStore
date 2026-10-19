package org.example.minionlinestore.api;

import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.model.Product;
import org.example.minionlinestore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value="/get-product-list")
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(value="/add-product")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

}
