package com.alechoskins.skilltreelpgbackend.services.Users;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;

public interface IUserServices {
    User create(User user) ;
    void delete(Long userId);

    //region QUERIES
    User findByUsername(String username);
    User findById(Long userId);
    //endregion
}
