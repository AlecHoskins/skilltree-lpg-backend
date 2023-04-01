package com.alechoskins.skilltreelpgbackend.services.Roles;

import com.alechoskins.skilltreelpgbackend.database.pojos.Role;

public interface IRoleServices {
    Role findByName(String username);
}
