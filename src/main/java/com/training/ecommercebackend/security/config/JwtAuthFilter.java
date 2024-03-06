package com.training.ecommercebackend.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService  jwtService ;

    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull  HttpServletResponse response,
          @NonNull  FilterChain filterChain) throws ServletException, IOException {

        // Authorization is a Header that contains a bearer JWT token
        final String authHeader = request.getHeader("Authorization");
        final  String jwt;
        final String userEmail;

        //check if there is a jwt token
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //extracted the token from the authHeader
        jwt = authHeader.substring(7);


        //extract a userEmail to jwt token
        userEmail = jwtService.extractUserName(jwt);

        //check if we have our userEmail and if user is not already authentificated in the security context
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            //loads the full user details from the databas using the UserDetailsService
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //check if the token is still valid or not and validates it
            if(jwtService.isTokenValid(jwt,userDetails)){

                //if token is  valid create our authentification Token for updating the security context
                //add userDetails,credentials and authorities
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                //adds  some additional authentification details to the token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                //update our security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }



    }
}
