package com.training.ecommercebackend.userauth;

import com.training.ecommercebackend.config.JwtService;
import com.training.ecommercebackend.repository.DaoUserRepository;
import com.training.ecommercebackend.user.Role;
import com.training.ecommercebackend.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





@Service
public class AuthService {

    private final DaoUserRepository daoUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public AuthService(DaoUserRepository daoUserRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.daoUserRepository = daoUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    //this method allow us to create a user saved it in a database and return the generated token
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );


        daoUserRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }


    public AuthenticationResponse logIn(AuthenticationRequest request) {
        return  null;
    }
}
