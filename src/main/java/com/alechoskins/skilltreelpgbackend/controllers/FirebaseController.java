package com.alechoskins.skilltreelpgbackend.controllers;

import com.alechoskins.skilltreelpgbackend.services.Firebase.FirebaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/firebase")
public class FirebaseController {

    @Autowired
    FirebaseServices firebaseServices;

    public void connectToFirebase(){}
}
