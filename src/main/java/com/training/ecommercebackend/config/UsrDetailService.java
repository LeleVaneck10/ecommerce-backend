package com.training.ecommercebackend.config;

import com.training.ecommercebackend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UsrDetailService {
    private final UserRepository repository;

    public UsrDetailService(UserRepository repository) {
        this.repository = repository;
    }


    // provides userDetails from database if user already exist
    @Bean
    public UserDetailsService userDetailsService(){
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
            }
        };
    }



}
