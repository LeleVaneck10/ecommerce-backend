package com.training.ecommercebackend.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class UserSecurityAppConfig {
    private final AuthenticationProvider authenticationProvider;
    private  final JwtAuthFilter jwtAthFilter;

    public UserSecurityAppConfig(AuthenticationProvider authenticationProvider, JwtAuthFilter jwtAthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAthFilter = jwtAthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        http
            .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/demo/**").permitAll()
                            .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class);


    http.csrf((csrf -> csrf.disable()));

    return http.build();
}



}
