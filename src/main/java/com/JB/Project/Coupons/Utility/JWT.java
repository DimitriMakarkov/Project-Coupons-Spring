package com.JB.Project.Coupons.Utility;

import com.JB.Project.Coupons.Beans.Credentials;
import com.JB.Project.Coupons.Beans.UserDetails;
import com.JB.Project.Coupons.Beans.UserType;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWT {
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private String encodedSecretKey = "uri+wants+to+lechartet+somthing+good+for+the+class";
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    public String generateToken(UserDetails userData) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", userData.getUserType());
        claims.put("userName", userData.getName());
        return createToken(claims, userData.getEmail());
    }

    public String generateToken(String token) {
        Map<String, Object> claims = new HashMap<>();
        Claims ourClaims = extractAllClaims(token);
        claims.put("userName", ourClaims.get("userName"));
        claims.put("userType", ourClaims.get("userType"));
        return createToken(claims, ourClaims.getSubject());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(60, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) throws SignatureException {
        return extractAllClaims(token).getSubject();
    }

    public java.util.Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }

    public String getUserType(String token) {
        Claims claims = extractAllClaims(token);
        return (String) claims.get("userType");
    }

    public boolean validateToken(String token, UserDetails userDetails) throws MalformedJwtException, SignatureException {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }

    public boolean validateToken(String token) throws MalformedJwtException, SignatureException{
        final Claims claims = extractAllClaims(token);
        return true;
    }

    public boolean checkUser(String token, UserType userType) throws LoginException {
        String newToken = token.replace("Bearer ", "");
        if (validateToken(newToken)) {
            if (!getUserType(newToken).equals(userType)) {
                throw new LoginException("User not allowed");
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        UserDetails zeev = new UserDetails("admin@admin.com","admin", UserType.ADMIN);
//        JWTutil jwtUtil = new JWTutil();
//        String token = jwtUtil.generateToken(zeev);
//        System.out.println("TOKEN: "+token);
    }
}
