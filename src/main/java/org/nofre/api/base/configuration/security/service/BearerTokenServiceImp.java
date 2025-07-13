package org.nofre.api.base.configuration.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nofre.api.base.configuration.security.BearerTokenService;
import org.nofre.api.base.feature.user.model.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BearerTokenServiceImp implements BearerTokenService {


    @Value("${security.secret-key}")
    private String secretKey;
    @Value("${security.jwt-expiration}")
    private long jwtExpiration;

    @Override
    public User getUserFromToken(String token) {
        //cargamos el usuario anonymous por defecto
        //TODO EL usuario anonimous se tiene que cargar por configuration
        //TODO tendria que tener un authoriti por defecto
        if (token != null && !token.isBlank()) {
            try {
                Claims claims = extractAllClaims(token);
                if (!claims.getExpiration().before(new Date())) {
                    String username = claims.getSubject();
                    List<?> roles = claims.get("roles", List.class);
                    List<SimpleGrantedAuthority> authorities = roles.stream().map(Object::toString).map(SimpleGrantedAuthority::new).toList();
                    return new User(username, "", authorities);
                }
            } catch (Exception e) {
                log.error("Invalid JWT signature: {}", e.getMessage(), e);
            }
        }
        return null;
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String generateToken(UserDto user) {
        Instant now = Instant.now();
        Map<String, Object> claims = Map.of("roles", user.getRoles().stream().map(role -> "ROLE_" + role.getName().toUpperCase()).toList());
        return Jwts.builder().subject(user.getEmail()).claims(claims).issuedAt(Date.from(now)).expiration(Date.from(now.plus(jwtExpiration, ChronoUnit.MILLIS))).signWith(getSignInKey()).compact();
    }


}
