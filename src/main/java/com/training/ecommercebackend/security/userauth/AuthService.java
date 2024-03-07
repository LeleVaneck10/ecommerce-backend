package com.training.ecommercebackend.security.userauth;

import com.training.ecommercebackend.model.Role;
import com.training.ecommercebackend.model.User;
import com.training.ecommercebackend.repository.DaoUserRepository;
import com.training.ecommercebackend.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





@Service
public class AuthService {

    private final DaoUserRepository daoUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthService(
                        DaoUserRepository daoUserRepository,
                        JwtService jwtService,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager) {

        this.daoUserRepository = daoUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    //this method allow us to create a user saved it in a database and return the generated token
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        daoUserRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }


// this methode help us to authenticate user based on their userName and password
    public AuthenticationResponse logIn(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        User user = daoUserRepository.findByEmail(request.getEmail()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);

    }
}
