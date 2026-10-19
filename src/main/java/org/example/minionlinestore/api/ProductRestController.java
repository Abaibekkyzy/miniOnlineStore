package org.example.minionlinestore.api;


import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/product")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> productList(){
        return productService.getProducts();
    }
    @GetMapping(value="{id}")
    public ProductDTO getProduct(@PathVariable(name="id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

}
