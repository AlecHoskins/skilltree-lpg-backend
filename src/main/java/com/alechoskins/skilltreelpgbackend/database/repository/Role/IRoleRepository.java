package com.alechoskins.skilltreelpgbackend.database.repository.Role;

import com.alechoskins.skilltreelpgbackend.database.pojos.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
