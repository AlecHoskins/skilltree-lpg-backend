package com.alechoskins.skilltreelpgbackend.services.Users;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.database.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServices implements IUserServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        try{
            return userRepository.getAllWhere("username", username).get(0);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public User findById(String userId) {
        return null;
    }

    @Override
    public String create(User user) {
        return null;
    }

    @Override
    public String update(String userId, User user) {
    return null;
    }

    @Override
    public void delete(String userId) {

    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
