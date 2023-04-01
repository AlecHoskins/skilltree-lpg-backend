package com.alechoskins.skilltreelpgbackend.services.Users;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;

public interface IUserServices {
    public User create(User user) ;
    public void delete(Long userId);

    //region QUERIES
    public User findByUsername(String username);
    public User findById(Long userId);
    //endregion
}
