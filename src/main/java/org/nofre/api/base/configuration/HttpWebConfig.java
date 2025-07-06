package org.nofre.api.base.configuration;

import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.security.filter.BearerTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class HttpWebConfig implements WebMvcConfigurer {

    private final BearerTokenFilter bearerTokenFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(anonymous -> anonymous
                        .principal("anonymous")
                        //TODO aqui se debe sacar del repositorio
                        .authorities("ROLE_ANONYMOUS")
                )
                //No le añado seguridad
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/transactions/lang/**",
                                "/api/auth/login",
                                "/actuator/**"  // <-- Esta es la forma correcta
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> c.isAnnotationPresent(ApiRestController.class));
    }

}