package com.alechoskins.skilltreelpgbackend.services.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseServices {

//    @PostConstruct
//    public void initialize() throws IOException {
//        FileInputStream serviceAccount =
//                new FileInputStream("src/main/resources/skilltree-lpg-backend-firebase-adminsdk-d5o0w-c6809820bc.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://skilltree-lpg-backend-default-rtdb.firebaseio.com")
//                .build();
//    FirebaseApp.initializeApp(options);
//    }
}
