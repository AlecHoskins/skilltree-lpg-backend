package com.alechoskins.skilltreelpgbackend.services.Users;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IUserServices extends UserDetailsService {
    public User findByUsername(String username);
    public User findById(String userId);
    public String create(User user);
    public String update(String userId, User user);
    public void delete(String userId);
}
