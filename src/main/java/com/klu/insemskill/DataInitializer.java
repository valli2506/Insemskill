package com.klu.insemskill;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initialize(
            UserRepository repository,
            PasswordEncoder encoder) {

        return args -> {
            if (repository.findByUsername("valli").isEmpty()) {
                AppUser user = new AppUser(
                    "valli",
                    encoder.encode("Demo@123")
                );

                repository.save(user);
            }
        };
    }
}