package org.nofre.api.base.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditorConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            if (user == null) {
                user = "anonymous";
            }
            return Optional.of(user);
        };
    }
}