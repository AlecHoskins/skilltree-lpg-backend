package com.alechoskins.skilltreelpgbackend.services.Authentication;

import com.alechoskins.skilltreelpgbackend.database.pojos.Role;
import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.global.AppEnums;
import com.alechoskins.skilltreelpgbackend.security.dto.LoginRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;
import com.alechoskins.skilltreelpgbackend.services.Jwt.JwtServices;
import com.alechoskins.skilltreelpgbackend.services.Roles.IRoleServices;
import com.alechoskins.skilltreelpgbackend.services.Users.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServices implements IAuthenticationServices {

    @Autowired
    private IUserServices userServices;
    @Autowired
    private IRoleServices roleServices;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    private final AuthenticationManager authManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws RoleNotFoundException {
        var userRole = roleServices.findByName(AppEnums.RoleNames.USER.name());
        if(userRole != null) {
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(userRole);
            var user = User.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonExpired(true)
                    .roles(roles)
                    .build();
            var createdUser = userServices.create(user);
            var jwtToken = jwtServices.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else{
            throw new RoleNotFoundException("Error, role not found in user creation.");
        }
    }

    @Override
    public AuthenticationResponse authenticate(LoginRequest request)  {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userServices.findByUsername(request.getUsername());
        var jwtToken = jwtServices.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
