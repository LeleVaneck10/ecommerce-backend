package com.training.ecommercebackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {


    // This key is used to sign and validate JWTs. It's crucial to keep this secret key confidential.
    private static final String SECRET_KEY= "2VqXOQoi5egrXQhTSdPyOUxbKZuLjFl7xz63eZoJlvdMXCPzp+nGJUND0A9PEmMbxpiSyhrW7OYdKm/fRqMmN62W263ICR81XMp16Y8WLI8=";



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
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // This method creates a JWT for a given user based on their details username.
    public String generateToken(UserDetails userDetails){
        return generateToken( new HashMap<>(),userDetails);
    }


    //generate the token out of extractClaims and userDetails  including additional custom claims in the JWT along with user details.
    public  String generateToken(Map<String,Object> extractClaims , UserDetails userDetails){

        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
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

    private Key getSignInKey() {

        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY) ;
        return Keys.hmacShaKeyFor(keyByte);
    }




}
