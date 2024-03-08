package com.training.ecommercebackend.security.config;

import com.training.ecommercebackend.model.User;
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
    private static final String SECRET_KEY= "fcfe740d1519d42386e320b1f5c45cabf15925d30bb508921c258543a6e5f2b1";



    // This method retrieves the username embedded within the JWT token.
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //this method extracts a specific claim value based on the provided function
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    //extracted all Claims
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // this method is use to create a singing part of  jwt witch is use to ensure that
    //the sender of a jwt is who claims to be and ensure that the message  does't change along the way
    private Key getSigningKey() {

        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY) ;
        return Keys.hmacShaKeyFor(keyByte);
    }


    // This method creates a JWT for a given user based on their details username.
    public String generateToken(User userDetails){
        return generateToken( new HashMap<>(),userDetails);
    }


    //generate the token out of extractClaims and userDetails  including additional custom claims in the JWT along with user details.
    public  String generateToken(Map<String,Object> extractClaims , User userDetails){

        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getFirstName())
                .setSubject(userDetails.getLastName())
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))//validityDay = 24hours + 1000ms
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
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
        return extractClaim(token,Claims::getExpiration);
    }




}
