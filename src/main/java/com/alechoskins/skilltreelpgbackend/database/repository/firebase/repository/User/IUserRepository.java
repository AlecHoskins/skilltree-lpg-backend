package com.alechoskins.skilltreelpgbackend.database.repository.firebase.repository.User;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IUserRepository {
    public User get(String userId) throws InterruptedException, ExecutionException;
    public List<User> getAllWhere(String field, Object value) throws InterruptedException, ExecutionException;
    public String save(User user) throws ExecutionException, InterruptedException;
    public void update(String userId, User user);
    public void delete(String userId);
}
