package com.alechoskins.skilltreelpgbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DatabaseConfig {

    //region FIRESTORE

    @Bean
    public Firestore firestore() throws IOException {

        var file = ResourceUtils.getFile("classpath:" + "skilltree-lpg-backend-firebase-adminsdk-d5o0w-c6809820bc.json");
        InputStream serviceAccount = new FileInputStream(file.getPath());
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("skilltree-lpg-backend")
                .build();
        var firestore = options.getService();
        return options.getService();
    }

    //endregion

}
