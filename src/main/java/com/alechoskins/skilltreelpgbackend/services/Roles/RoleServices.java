package com.alechoskins.skilltreelpgbackend.services.Roles;

import com.alechoskins.skilltreelpgbackend.database.pojos.Role;
import com.alechoskins.skilltreelpgbackend.database.repository.Role.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServices implements IRoleServices {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Role findByName(String username) {
        return roleRepository.findByName(username).orElse(null);
    }
}
