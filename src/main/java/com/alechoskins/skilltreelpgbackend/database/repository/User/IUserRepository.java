package com.alechoskins.skilltreelpgbackend.database.repository.User;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;

import java.util.List;
import java.util.Map;

public interface IUserRepository {
    List<User> getAllWhere(String username, String username1);
}
