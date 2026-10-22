package org.example.minionlinestore.repository;

import jakarta.transaction.Transactional;
import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    @Query(
            value="" +
                    "SELECT mm " +
                    "FROM Product mm " +
                    "WHERE mm.quantity > 0 AND LOWER(mm.productName) LIKE :searchName " +
                    "ORDER BY mm.price DESC")
    List<ProductDTO> searchProduct(@Param("searchName") String productName);
}
