package com.alechoskins.skilltreelpgbackend.database.repository.User;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
