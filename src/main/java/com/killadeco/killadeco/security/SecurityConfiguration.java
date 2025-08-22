package com.killadeco.killadeco.security;

import com.killadeco.killadeco.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // ENDPOINTS PÚBLICOS --------------------------------------------------
                        .requestMatchers(publicEndpoints()).permitAll()
                        // ENDPOINTS CON PERMISOS ESPECÍFICOS ---------------------------------------------------
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
                        // USER ENDPOINTS ---------------------------------------------------
                        .requestMatchers(HttpMethod.GET, "/user/{id}").hasAnyAuthority("ADMIN", "SUPER_ADMIN", "CLIENT")
                        .requestMatchers(HttpMethod.POST, "/user/images/upload").hasAnyAuthority("ADMIN", "SUPER_ADMIN", "CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/user/update").hasAnyAuthority("ADMIN", "SUPER_ADMIN", "CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/user/update-rol").hasAuthority("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/list").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/list-active").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/change-status").hasAnyAuthority("ADMIN", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").hasAuthority("SUPER_ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/auth/oauth-success", true)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            String jsonResponse = String.format(
                                    "{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"%s\"}",
                                    "No autorizado: Token JWT requerido"
                            );
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write(jsonResponse);
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            String jsonResponse = String.format(
                                    "{\"status\": 403, \"error\": \"Forbidden\", \"message\": \"%s\"}",
                                    "Acceso denegado: No tienes permisos suficientes"
                            );
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write(jsonResponse);
                        })
                )
                .build();
    }

    private static String[] publicEndpoints() {
        return new String[]{
                "/v3/api-docs/**",
                "/swagger-ui.html", // Agregamos esta línea
                "/swagger-ui/**",
                "/api-docs.yaml",
                "/webjars/**",
                "/swagger-ui-custom.html",
                "/public/lead/create",
                "/auth/oauth-success",
                "/auth/login",
                "/auth/generate-reset-token",
                "/auth/reset-password",
                "/login/oauth2/**",
                "/oauth2/**"
        };
    }
}