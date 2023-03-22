package com.alechoskins.skilltreelpgbackend.controllers;

import com.alechoskins.skilltreelpgbackend.security.Endpoints;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;
import com.alechoskins.skilltreelpgbackend.services.Authentication.IAuthenticationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.security.sasl.AuthenticationException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationServices authenticationService;

    @GetMapping(Endpoints.AUTH_REGISTER)
    public String registerView() {
        return "<h1>Register Page</h1> <style>h1{color: blue;}</style>";
    }

    @PostMapping(Endpoints.AUTH_AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws AuthenticationException {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        } catch (Exception e) {
            throw new AuthenticationException("Could not authenticate request");
        }
    }
}
