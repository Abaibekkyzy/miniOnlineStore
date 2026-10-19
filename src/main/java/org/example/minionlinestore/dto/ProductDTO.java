package org.example.minionlinestore.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private int price;
    private int code;
    private int quantity;
}
