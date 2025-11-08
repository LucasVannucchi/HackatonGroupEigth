package com.groupEight.TaskManagement.services;

import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.groupEight.TaskManagement.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {


    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioModel usuarioModel){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("app-hackaton")
                    .withSubject(usuarioModel.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Falha ao gerar o token JWT");
        }
    }

    public String tokenValidation(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String valid = JWT.require(algorithm)
                    .withIssuer("app-hackaton")
                    .build()
                    .verify(token)
                    .getSubject();
            return valid;
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3"));
    }
}
