package org.nofre.api.base.configuration;

import lombok.RequiredArgsConstructor;
import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.configuration.security.filter.BearerTokenFilter;
import org.nofre.api.base.configuration.security.filter.ObservabilityCaptureFilter;
import org.nofre.api.base.configuration.security.filter.ObservabilityInjectFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class HttpWebConfig implements WebMvcConfigurer {

    private final BearerTokenFilter bearerTokenFilter;
    private final ObservabilityInjectFilter observabilityInjectFilter;
    private final ObservabilityCaptureFilter observabilityCaptureFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(anonymous -> anonymous
                        .principal("anonymous")
                        .authorities("ROLE_ANONYMOUS")
                )
                //No le añado seguridad
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/transactions/lang/**",
                                "/api/auth/login",
                                "/api/auth/register",
                                "/api/frontend-resources/me",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(observabilityCaptureFilter, HeaderWriterFilter.class)
                .addFilterAfter(observabilityInjectFilter, HeaderWriterFilter.class)
                .build();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> c.isAnnotationPresent(ApiRestController.class));
    }

}