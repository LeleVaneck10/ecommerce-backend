package com.training.ecommercebackend.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // This key is used to sign and validate JWTs. It's crucial to keep this secret key confidential.
    //we need to used a sign-in key to generated or decode a token
    private static final String SECRET_KEY= "9d3ef6e6a5a934c63a8c5ec877cff75f2ebdd8ad42f8993418d1f47c56faec5a";



    // This method retrieves the username embedded within the JWT token.
    public String extractUserName(String token) {

        return extractClaims(token, Claims::getSubject);
    }


    //this method extracts a specific claim value based on the provided function
    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractallClaims(token);

        return claimsResolver.apply(claims);
    }

    //extracted all Claims
    private Claims extractallClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // this method is use to created a signature part of a jwt token which is used to verify that a sender of a jwt
    // is who it's claims to be and ensure that the message dosn't change along the way
    private Key signingKey() {

        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY) ;
        return Keys.hmacShaKeyFor(keyByte);
    }


    // This method generate a JWT token  for a given user based on their details username.
    public String generateToken(UserDetails userDetails){

        return generateToken( new HashMap<>(),userDetails);
    }


    //generate the token out of extraClaims and userDetails  including additional custom claims in the JWT along with user details.
    public  String generateToken(Map<String,Object> extraClaims , UserDetails userDetails){

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //validationTokenDate = 24 hours and 1000 ms
                .signWith(signingKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    /* this method checks if a JWT is valid for a specific user.
     * It ensures the token hasn't expired and belongs to the expected user.
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {

        return extraExpiration(token).before(new Date());
    }

    private Date extraExpiration(String token) {

        return extractClaims(token,Claims::getExpiration);
    }




}
//generating token
//extracting information from the token
//validating the token
//
