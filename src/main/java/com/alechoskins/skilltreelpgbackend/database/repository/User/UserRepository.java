package com.alechoskins.skilltreelpgbackend.database.repository.User;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public List<User> getAllWhere(String username, String username1) {
        return null;
    }
}
