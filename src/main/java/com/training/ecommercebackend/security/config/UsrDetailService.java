package com.training.ecommercebackend.security.config;

import com.training.ecommercebackend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsrDetailService {
    private final UserRepository repository;

    public UsrDetailService(UserRepository repository) {
        this.repository = repository;
    }


    // provides userDetails from database if user already exist
    @Bean
    public UserDetailsService userDetailsService(){
            return username -> repository
                                        .findByEmail(username)
                                        .orElseThrow(() ->new UsernameNotFoundException("USER NOT FOUND"));

    }

    //the authenticationProvider is a data access object responsible to fetch userDetails and encoded password
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // this method helps us to authenticated the user passed username and password
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
