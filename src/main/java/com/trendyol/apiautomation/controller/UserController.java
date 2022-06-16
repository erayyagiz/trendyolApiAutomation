package com.trendyol.apiautomation.controller;

import com.trendyol.apiautomation.model.User;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<User> login(@RequestBody User user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if (user.getUsername() == null || user.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else{
            String getToken = getJWTToken(user.getUsername(), user.getPassword());
            responseHeaders.set("Authorization", getToken);
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(user);
        }
    }

    private String getJWTToken(String username,String password) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username+password)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
