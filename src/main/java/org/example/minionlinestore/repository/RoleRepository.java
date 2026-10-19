package org.example.minionlinestore.repository;

import jakarta.transaction.Transactional;
import org.example.minionlinestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
}
