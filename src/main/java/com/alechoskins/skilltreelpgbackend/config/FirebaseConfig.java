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

    //firebase string:
    //APPLICATION_PROPERTIES=#logging.level.root=DEBUG logging.level.org.springframework= DEBUG management.endpoints.web.exposure.include=* ;
    // FIREBASE_AUTH_PROVIDER_CERT_URL=https://www.googleapis.com/oauth2/v1/certs;FIREBASE_AUTH_URI=https://accounts.google.com/o/oauth2/auth;
    // FIREBASE_CLIENT_CERT_URL=https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-d5o0w%40skilltree-lpg-backend.iam.gserviceaccount.com;
    // FIREBASE_CLIENT_EMAIL=firebase-adminsdk-d5o0w@skilltree-lpg-backend.iam.gserviceaccount.com;
    // FIREBASE_CLIENT_ID=106731963664546805129;
    // FIREBASE_PRIVATE_KEY=-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgv/f+saZ1cyG4\nxECOIaIM3/YOmGskwRneaFPF+4AVGrONCdM3C1XBP0TTdG8rM8ygiHxWYh45mxpL\nMaEE7ixXCLE1+TEj7y2+6uYt1QzjmUUBaVdHULJenC0ROkTrTL67V+312hh9U5ej\neuAZwrAvu7FM0phDyIDznY9518SOoHnqMBmPXiE+vm7FIv4Eykv4DnS3S6ufT0sg\nAQguqiERWUM5GvOFehEKX/OcvAQKDG3XJ9cipKbrTYrUIuZesnFhK9zsR2X8fMCc\nA97Z8EfKfq3lYkHJsYFha0mQmah5OYCod/1JPFjFFBPBzCXstgtq7YH+iaD/3nQU\n/rB8HL9HAgMBAAECggEACiVjuUMsPwCH0dotOutbh5WBOdWb+aNohxWtp/LiWDKp\nv46tsq03+e0M4o3YUHFCCqXity8VOkyUYcedoqqHyOyHBuGWHO8FQ6Ye5iiq2hi7\n9sVX2Wv5/ie8Xnh/jhx3c2qu3dQotIxYp1xjlayoozKlE7S9IJYgsX+x5R5dzSSx\nPibqI6Wm7ahbPbGPv6JgSAeOH0Mc/kkkw3Scopi6IhFQ6kSwo6UiV6wTTBgSEICk\nl83j9S/TsQYGVYBgRK6/GvtHZLCHjWJiatoVSGQfGzAGg2kVnBa4wMtHYMGQTosw\nOnhhTjj/N35uHpdRo/BJjuQjg831U0i6UJZAWeWItQKBgQDRfUTR2dz1AcKVU3yt\npOn8VOffQNSVNbdrhxAj/Tpq4aCvB0V9T+du71tTLsq12kP7AHTYx3NtosfrPnFG\nIzup8nPAFbZH0ydCULH8PKt/DOMbh6wSQOt+baG14T5WwR69NrIhII6bkVYUaLh8\nvFes5G224ixBUdFAkCAMw7hXGwKBgQDEcH+zXAcAR00tNNNvFv7L0lHNozIY9MVj\nmTopRzrAkLvUPlwS83WbxiTHiEDSYAMM+WNzgWvms/I+6fjvahiBdDQVCs4f3mQ0\nOwMnvrG1EnK2kxWF42tDMO5L/EmFwqghQcWgVnUFvEoDHy8vz3zBqLYLStA631eV\np24Zk7MfRQKBgQCTRpgtWBbpAJXF2W3bvgbp6N8iqzdItvz+UXPdeVeqcHlA08Hc\nuWIhMZFox4qnko0cV1POW1ohB8G49qs3/ekiL1uE/T6q/B2rl4OpqxV+OpcncZhd\nNNrahUSc03+6CgcMSvmsJHFdmS2FJ6Ibv2V8HROs8RDf0x9jXm/JyYc9ZQKBgELf\nITnHrEJ2A1nOT+PTai2sjVh5o8ZHHSMIuHnw25lOLALWr7Vr16+Nxlt+kMKNGLzD\n8Q0TE85Xl7G/KJUoAuueyVQYd471wBrfs/VFv20quI5ah8sqXgtjjtujKMw2NdIM\nd+BbTj8DmBZz+TZHZervKfmyICzKdyBk/OAMHcAZAoGAb7F9gOFa0jwtOrV1wd28\nH4pT5ptX24ddF9xV5rGArGkZJLJn+B6msGbHQ8EVJclkIr0BFyr6LLOKa9qzr41i\nyyFuEWxFhHaVS3b63JFh/JLZe0QrUItnyanlLcnRPues7PuwUTD73s2yeLiaKXqp\nitenYFYvxWzy6QGgUww0Tz8=\n-----END PRIVATE KEY-----\n;
    // FIREBASE_PRIVATE_KEY_ID=c6809820bc584dbb8b81929116cad30b982af48c;
    // FIREBASE_PROJECT_ID=skilltree-lpg-backend;
    // FIREBASE_TOKEN_URI=https://oauth2.googleapis.com/token;
    // FIREBASE_TYPE=service_account;
    // JWT_SECRET_KEY=33743677397A244226452948404D635166546A576E5A7234753778214125442A472D4A614E645267556B58703273357638792F423F4528482B4D6250655368566D597133743677397A24432646294A404E635266546A576E5A7234753778214125442A472D4B6150645367566B58703273357638792F423F4528482B4D6251655468576D5A7133743677397A24432646294A404E635266556A586E327235753778214125442A472D4B6150645367566B59703373367639792F423F4528482B4D6251655468576D5A7134743777217A25432646294A404E635266556A586E3272357538782F413F4428472D4B6150645367566B59703373367639792442264529

    //replaces the placeholders with the environment variables for security
    private InputStream getFirebaseCredentialsFromEnv() throws URISyntaxException {
        try{
            URL url = getClass().getClassLoader().getResource("skilltree-lpg-backend-firebase-adminsdk-d5o0w-c6809820bc.json");
            assert url != null;
            Path path = Paths.get(url.toURI());
            String jsonContent = Files.readString(path);
            String jsonContentWithEnvVars = jsonContent
                    .replace("TYPE_PLACEHOLDER", "service_account")
                    .replace("PROJECT_ID_PLACEHOLDER", "skilltree-lpg-backend")
                    .replace("PRIVATE_KEY_ID_PLACEHOLDER", "FIREBASE_PRIVATE_KEY_ID")
                    .replace("PRIVATE_KEY_PLACEHOLDER", "-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgv/f+saZ1cyG4\\nxECOIaIM3/YOmGskwRneaFPF+4AVGrONCdM3C1XBP0TTdG8rM8ygiHxWYh45mxpL\\nMaEE7ixXCLE1+TEj7y2+6uYt1QzjmUUBaVdHULJenC0ROkTrTL67V+312hh9U5ej\\neuAZwrAvu7FM0phDyIDznY9518SOoHnqMBmPXiE+vm7FIv4Eykv4DnS3S6ufT0sg\\nAQguqiERWUM5GvOFehEKX/OcvAQKDG3XJ9cipKbrTYrUIuZesnFhK9zsR2X8fMCc\\nA97Z8EfKfq3lYkHJsYFha0mQmah5OYCod/1JPFjFFBPBzCXstgtq7YH+iaD/3nQU\\n/rB8HL9HAgMBAAECggEACiVjuUMsPwCH0dotOutbh5WBOdWb+aNohxWtp/LiWDKp\\nv46tsq03+e0M4o3YUHFCCqXity8VOkyUYcedoqqHyOyHBuGWHO8FQ6Ye5iiq2hi7\\n9sVX2Wv5/ie8Xnh/jhx3c2qu3dQotIxYp1xjlayoozKlE7S9IJYgsX+x5R5dzSSx\\nPibqI6Wm7ahbPbGPv6JgSAeOH0Mc/kkkw3Scopi6IhFQ6kSwo6UiV6wTTBgSEICk\\nl83j9S/TsQYGVYBgRK6/GvtHZLCHjWJiatoVSGQfGzAGg2kVnBa4wMtHYMGQTosw\\nOnhhTjj/N35uHpdRo/BJjuQjg831U0i6UJZAWeWItQKBgQDRfUTR2dz1AcKVU3yt\\npOn8VOffQNSVNbdrhxAj/Tpq4aCvB0V9T+du71tTLsq12kP7AHTYx3NtosfrPnFG\\nIzup8nPAFbZH0ydCULH8PKt/DOMbh6wSQOt+baG14T5WwR69NrIhII6bkVYUaLh8\\nvFes5G224ixBUdFAkCAMw7hXGwKBgQDEcH+zXAcAR00tNNNvFv7L0lHNozIY9MVj\\nmTopRzrAkLvUPlwS83WbxiTHiEDSYAMM+WNzgWvms/I+6fjvahiBdDQVCs4f3mQ0\\nOwMnvrG1EnK2kxWF42tDMO5L/EmFwqghQcWgVnUFvEoDHy8vz3zBqLYLStA631eV\\np24Zk7MfRQKBgQCTRpgtWBbpAJXF2W3bvgbp6N8iqzdItvz+UXPdeVeqcHlA08Hc\\nuWIhMZFox4qnko0cV1POW1ohB8G49qs3/ekiL1uE/T6q/B2rl4OpqxV+OpcncZhd\\nNNrahUSc03+6CgcMSvmsJHFdmS2FJ6Ibv2V8HROs8RDf0x9jXm/JyYc9ZQKBgELf\\nITnHrEJ2A1nOT+PTai2sjVh5o8ZHHSMIuHnw25lOLALWr7Vr16+Nxlt+kMKNGLzD\\n8Q0TE85Xl7G/KJUoAuueyVQYd471wBrfs/VFv20quI5ah8sqXgtjjtujKMw2NdIM\\nd+BbTj8DmBZz+TZHZervKfmyICzKdyBk/OAMHcAZAoGAb7F9gOFa0jwtOrV1wd28\\nH4pT5ptX24ddF9xV5rGArGkZJLJn+B6msGbHQ8EVJclkIr0BFyr6LLOKa9qzr41i\\nyyFuEWxFhHaVS3b63JFh/JLZe0QrUItnyanlLcnRPues7PuwUTD73s2yeLiaKXqp\\nitenYFYvxWzy6QGgUww0Tz8=\\n-----END PRIVATE KEY-----\\n")
                    .replace("CLIENT_EMAIL_PLACEHOLDER","firebase-adminsdk-d5o0w@skilltree-lpg-backend.iam.gserviceaccount.com")
                    .replace("CLIENT_ID_PLACEHOLDER", "106731963664546805129")
                    .replace("AUTH_URI_PLACEHOLDER", "FIREBASE_AUTH_URI")
                    .replace("TOKEN_URI_PLACEHOLDER", "FIREBASE_TOKEN_URI")
                    .replace("AUTH_PROVIDER_X509_CERT_URL_PLACEHOLDER", "FIREBASE_AUTH_PROVIDER_CERT_URL")
                    .replace("CLIENT_X509_CERT_URL_PLACEHOLDER", "FIREBASE_CLIENT_CERT_URL");
            /*
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
            */
            return new ByteArrayInputStream(jsonContentWithEnvVars.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            throw new URISyntaxException("","\n\n\nCUSTOM ERROR reading firebase json file \n\n\n");
        }
    }
}

