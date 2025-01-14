package andrehsvictor.anitrace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtDecoder jwtDecoder;

    private static final String[] ALLOWED_PATHS_WITH_ANY_METHOD = {
            "/",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

    private static final String[] ALLOWED_PATHS_WITH_GET_METHOD = {

    };

    private static final String[] ALLOWED_PATHS_WITH_POST_METHOD = {
            "/api/v1/token",
            "/api/v1/token/refresh",
            "/api/v1/account",
    };

    private static final String[] ALLOWED_PATHS_WITH_DELETE_METHOD = {
            "/api/v1/token",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.GET, ALLOWED_PATHS_WITH_GET_METHOD).permitAll();
            authorize.requestMatchers(HttpMethod.POST, ALLOWED_PATHS_WITH_POST_METHOD).permitAll();
            authorize.requestMatchers(HttpMethod.DELETE, ALLOWED_PATHS_WITH_DELETE_METHOD).permitAll();
            authorize.requestMatchers(ALLOWED_PATHS_WITH_ANY_METHOD).permitAll();
            authorize.anyRequest().authenticated();
        });
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder)));
        return http.build();
    }

}
