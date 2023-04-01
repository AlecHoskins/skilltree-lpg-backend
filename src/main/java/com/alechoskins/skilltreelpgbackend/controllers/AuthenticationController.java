package com.alechoskins.skilltreelpgbackend.controllers;

import com.alechoskins.skilltreelpgbackend.global.Endpoints;
import com.alechoskins.skilltreelpgbackend.security.dto.LoginRequest;
import com.alechoskins.skilltreelpgbackend.security.dto.AuthenticationResponse;
import com.alechoskins.skilltreelpgbackend.security.dto.RegisterRequest;
import com.alechoskins.skilltreelpgbackend.services.Authentication.IAuthenticationServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.security.sasl.AuthenticationException;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationServices authenticationService;

    @PostMapping(Endpoints.AUTH_REGISTER)
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) throws RoleNotFoundException {
        var authResponse = authenticationService.register(request);
        return ResponseEntity.ok(authResponse);

    }

    @PostMapping(Endpoints.AUTH_AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
