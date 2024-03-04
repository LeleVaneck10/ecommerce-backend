package com.training.ecommercebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityAppConfig {

public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    http
            .authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET , "urlPath").hasRole("")
            );


    http.csrf((csrf -> csrf.disable()));

    return http.build();
}



}
