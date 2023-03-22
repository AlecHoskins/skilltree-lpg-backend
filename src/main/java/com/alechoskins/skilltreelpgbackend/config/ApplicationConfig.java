package com.alechoskins.skilltreelpgbackend.config;

import com.alechoskins.skilltreelpgbackend.database.repository.firebase.repository.User.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Autowired
    private final IUserRepository userRepository;

    public void initialize() throws IOException {
        try{
            Path applicationPropertiesPath  = Path.of(
                    ResourceUtils.getFile("classpath:" + "application.properties")
                            .getPath()
            );
            String fileContent = Files.readString(applicationPropertiesPath);

            fileContent = fileContent
                    .replace("APPLICATION_PROPERTIES_PLACEHOLDER", System.getenv("APPLICATION_PROPERTIES"));
            Files.writeString(applicationPropertiesPath, fileContent, StandardCharsets.UTF_8);
        }
        catch (Exception e){
            throw new IOException("CUSTOM ERROR populating firebase json with env variables");
        }
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return  username -> {
            try {
                return userRepository.getAllWhere("username",username).get(0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
