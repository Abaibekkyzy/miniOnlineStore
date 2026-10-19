package org.example.minionlinestore.repository;

import jakarta.transaction.Transactional;
import org.example.minionlinestore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

}
