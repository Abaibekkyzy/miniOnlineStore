package org.example.minionlinestore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_products")
@Getter
@Setter
public class Product extends BaseModel{

    @Column(name = "name")
    private String productName;

    @Column(name = "code")
    private String productCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;
}
