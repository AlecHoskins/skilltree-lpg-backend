package com.alechoskins.skilltreelpgbackend.controllers;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.global.Endpoints;
import com.alechoskins.skilltreelpgbackend.services.Users.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping(Endpoints.USER_GET)
    public User getUser(@PathVariable String id) {
        return userServices.findById(id);
    }

    @GetMapping(Endpoints.USER_GET_ALL)
    public User getAllUsers(@PathVariable String id) {
        return userServices.findById(id);
    }

    /*
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    */
}
