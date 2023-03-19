package com.alechoskins.skilltreelpgbackend.services.Authentication;

import com.alechoskins.skilltreelpgbackend.database.pojos.User;
import com.alechoskins.skilltreelpgbackend.security.Role;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;
import com.alechoskins.skilltreelpgbackend.services.Jwt.JwtServices;
import com.alechoskins.skilltreelpgbackend.services.Users.IUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AuthenticationServices implements IAuthenticationServices {

    @Autowired
    private IUserServices userServices;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    private final AuthenticationManager authManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var roles = new ArrayList<Role>();
        roles.add(Role.USER);
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .roles(roles)
                .build();
        userServices.create(user);

        var jwtToken = jwtServices.generateToken((UserDetails) user);
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request)  {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userServices.findByUsername(request.getEmail());
        /*TODO Change to username*/
        var jwtToken = jwtServices.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
