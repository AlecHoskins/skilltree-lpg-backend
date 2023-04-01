package com.alechoskins.skilltreelpgbackend.services.Authentication;

import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.LoginRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;

import javax.management.relation.RoleNotFoundException;

public interface IAuthenticationServices {

    AuthenticationResponse register(RegisterRequest request) throws RoleNotFoundException;

    AuthenticationResponse authenticate(LoginRequest request);
}
