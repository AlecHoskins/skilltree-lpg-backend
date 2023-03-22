package com.alechoskins.skilltreelpgbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() throws IOException {
        try {
            InputStream serviceAccount = getFirebaseCredentialsFromEnv();//resource.getInputStream();
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirestoreOptions options = FirestoreOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId("skilltree-lpg-backend")
                    .build();
            return options.getService();
        } catch (Exception e) {
            throw new IOException("\n\n\nCUSTOM ERROR setting credentials from firebase json\n\n\n", e);
        }
    }
    //replaces the placeholders with the environment variables for security
    private InputStream getFirebaseCredentialsFromEnv() throws URISyntaxException {
        try{
            URL url = getClass().getClassLoader().getResource("skilltree-lpg-backend-firebase-adminsdk-d5o0w-c6809820bc.json");
            assert url != null;
            Path path = Paths.get(url.toURI());
            String jsonContent = Files.readString(path);
            String jsonContentWithEnvVars = jsonContent
                    .replace("TYPE_PLACEHOLDER", System.getenv("FIREBASE_TYPE"))
                    .replace("PROJECT_ID_PLACEHOLDER", System.getenv("FIREBASE_PROJECT_ID"))
                    .replace("PRIVATE_KEY_ID_PLACEHOLDER", System.getenv("FIREBASE_PRIVATE_KEY_ID"))
                    .replace("PRIVATE_KEY_PLACEHOLDER", System.getenv("FIREBASE_PRIVATE_KEY"))
                    .replace("CLIENT_EMAIL_PLACEHOLDER", System.getenv("FIREBASE_CLIENT_EMAIL"))
                    .replace("CLIENT_ID_PLACEHOLDER", System.getenv("FIREBASE_CLIENT_ID"))
                    .replace("AUTH_URI_PLACEHOLDER", System.getenv("FIREBASE_AUTH_URI"))
                    .replace("TOKEN_URI_PLACEHOLDER", System.getenv("FIREBASE_TOKEN_URI"))
                    .replace("AUTH_PROVIDER_X509_CERT_URL_PLACEHOLDER", System.getenv("FIREBASE_AUTH_PROVIDER_CERT_URL"))
                    .replace("CLIENT_X509_CERT_URL_PLACEHOLDER", System.getenv("FIREBASE_CLIENT_CERT_URL"));
            return new ByteArrayInputStream(jsonContentWithEnvVars.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            throw new URISyntaxException("","\n\n\nCUSTOM ERROR reading firebase json file \n\n\n");
        }
    }
}

