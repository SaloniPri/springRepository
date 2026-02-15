package com.employee.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.employee.Entity.Employee;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;


@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algokey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int duration;
    private Algorithm algorithm;
    @PostConstruct
    public void PostConstruct() throws RuntimeException, UnsupportedEncodingException {
        algorithm =Algorithm.HMAC256(algokey);

    }
    //generate token
    public String generateToken(Employee employee){
        return JWT.create()
                .withClaim("username",employee.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+duration))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getUsername(String generateToken){
        DecodedJWT decode=JWT.require(algorithm)
                .withIssuer(issuer)
                .build().verify(generateToken);
                return decode.getClaim("username").asString();

    }



}
