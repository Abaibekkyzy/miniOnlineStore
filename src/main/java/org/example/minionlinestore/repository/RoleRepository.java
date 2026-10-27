package org.example.minionlinestore.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.dto.UserDTO;
import org.example.minionlinestore.model.Role;
import org.example.minionlinestore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
