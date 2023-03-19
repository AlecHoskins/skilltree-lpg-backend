package com.alechoskins.skilltreelpgbackend.services.Authentication;

import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;

import java.util.concurrent.ExecutionException;

public interface IAuthenticationServices {

    public AuthenticationResponse register(RegisterRequest request) throws ExecutionException, InterruptedException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws ExecutionException, InterruptedException;
}
