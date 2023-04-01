package com.alechoskins.skilltreelpgbackend.services.Users;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.database.repository.User.IUserRepository;
import com.alechoskins.skilltreelpgbackend.global.ErrorHandling.exceptions.UsernameAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements IUserServices{

    @Autowired
    IUserRepository userRepository;

    //region METHODS
    @Transactional
    public User create(User user) {
        var userExists = userRepository.existsByUsername(user.getUsername());
        if(!userExists){
            return userRepository.save(user);
        }
        throw new UsernameAlreadyExistsException("User already Exists");
    }
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
    //endregion

    //region QUERIES
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    //endregion
}
